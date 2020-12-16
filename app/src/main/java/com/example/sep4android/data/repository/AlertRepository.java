package com.example.sep4android.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.sep4android.data.model.AlertValue;


public class AlertRepository {
    private AlertValueDao alertValueDao;
    private LiveData<AlertValue> alertValueLiveData;

    private static AlertRepository instance;

    public AlertRepository(Application application) {

        SEP4Database sep4Database= SEP4Database.getInstance(application);
        alertValueDao= sep4Database.alertValueDao();
    }

    public static synchronized AlertRepository getInstance(Application application){
        if (instance==null){
            instance= new AlertRepository(application);
        }
        return instance;
    }


    public LiveData<AlertValue> getAlertValueLiveData(long userId) {
        alertValueLiveData= alertValueDao.getAlertValue(userId);
        return alertValueLiveData;
    }



    public void insert(AlertValue... alertValues){
        new InsertAsync(alertValueDao).execute(alertValues);
    }

    public void update(AlertValue... alertValues){
        new UpdateAsync(alertValueDao).execute(alertValues);
    }


    private class InsertAsync extends AsyncTask<AlertValue, Void, Void>{

        private AlertValueDao alertValueDao;

        public InsertAsync(AlertValueDao alertValueDao) {
            this.alertValueDao = alertValueDao;
        }

        @Override
        protected Void doInBackground(AlertValue... alertValues) {
            alertValueDao.insert(alertValues);
            return null;
        }
    }

    private class UpdateAsync extends AsyncTask<AlertValue, Void, Void>{

        private AlertValueDao alertValueDao;

        public UpdateAsync(AlertValueDao alertValueDao) {
            this.alertValueDao = alertValueDao;
        }

        @Override
        protected Void doInBackground(AlertValue... alertValues) {
            alertValueDao.update(alertValues);
            return null;
        }
    }
}
