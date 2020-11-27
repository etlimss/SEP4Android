package com.example.sep4android.client.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.client.model.Measure;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Measure> measureData= new MutableLiveData<>() ;

    public MutableLiveData<Measure> getMeasureData() {
        return measureData;
    }

    public void updateMeasureFromServer(){
        Measure measure= new Measure(23.6, 101, 1000);
        measureData.setValue(measure);
    }
}
