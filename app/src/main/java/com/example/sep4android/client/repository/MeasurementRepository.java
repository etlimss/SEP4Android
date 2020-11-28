package com.example.sep4android.client.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.sep4android.client.model.Measurements;

import java.util.List;

public class MeasurementRepository {
    private MeasurementsDao measurementsDao;
    private LiveData<List<Measurements>> allMeasurements;

    private static MeasurementRepository instance;


    private MeasurementRepository(Application application){
        MeasurementsDatabase measurementsDatabase= MeasurementsDatabase.getInstance(application);
        measurementsDao= measurementsDatabase.measurementsDao();
        allMeasurements= measurementsDao.getAllMeasurements();
    }

    public static synchronized MeasurementRepository getInstance(Application application){
        if (instance==null){
            instance= new MeasurementRepository(application);
        }

        return instance;
    }

    public LiveData<List<Measurements>> getAllMeasurements(){
        return allMeasurements;
    }

    public void insert(Measurements measurements){
        new InsertAsync(measurementsDao).execute(measurements);
    }

    public void delete(Measurements measurements){
       new DeleteAsync(measurementsDao).execute(measurements);
    }

    public void deleteAllMeasurements(){
        new DeleteAllMeasurementAsync(measurementsDao).execute();
    }



    private static class InsertAsync extends AsyncTask<Measurements, Void, Void> {
        private MeasurementsDao measurementsDao;

        private InsertAsync(MeasurementsDao measurementsDao){
            this.measurementsDao= measurementsDao;
        }

        @Override
        protected Void doInBackground(Measurements... measurements) {
            measurementsDao.insert(measurements);
            return null;
        }
    }

    private static class DeleteAllMeasurementAsync extends AsyncTask<Void,Void,Void> {
        private MeasurementsDao measurementsDao;

        private DeleteAllMeasurementAsync(MeasurementsDao measurementsDao) {
            this.measurementsDao=measurementsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
           measurementsDao.deleteAllMeasurements();
            return null;
        }
    }

    private static class DeleteAsync extends AsyncTask<Measurements, Void, Void> {
        private MeasurementsDao measurementsDao;

        private DeleteAsync(MeasurementsDao measurementsDao){
            this.measurementsDao= measurementsDao;
        }

        @Override
        protected Void doInBackground(Measurements... measurements) {
            measurementsDao.delete(measurements);
            return null;
        }
    }


}
