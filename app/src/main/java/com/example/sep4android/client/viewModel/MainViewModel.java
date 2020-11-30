package com.example.sep4android.client.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.repository.MeasurementRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Measurements> measureData= new MutableLiveData<>() ;
    private MeasurementRepository measurementRepository;




    public MainViewModel(@NonNull Application application) {
        super(application);
        measurementRepository= MeasurementRepository.getInstance(application);
    }

    public MutableLiveData<Measurements> getMeasureData() {
        return measureData;
    }

    public void updateMeasureFromServer(){
        Measurements measurements = new Measurements(23.6, 101, 1000);
        measureData.setValue(measurements);
    }

    public LiveData<List<Measurements>> getAllMeasurements(){
        return measurementRepository.getAllMeasurements();
    }

    public void insert(Measurements measurements){
        measurementRepository.insert(measurements);
    }

    public void delete(Measurements measurements){
        measurementRepository.delete(measurements);
    }

    public void deleteAllMeasurements(){
        measurementRepository.deleteAllMeasurements();
    }
}
