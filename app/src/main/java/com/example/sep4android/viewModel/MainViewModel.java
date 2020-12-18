package com.example.sep4android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.data.networking.ClientRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ClientRepository clientRepository;

    private MutableLiveData<Measurements> measurementsMutableLiveData= new MutableLiveData<>() ;

    public MainViewModel(@NonNull Application application) {
        super(application);
        clientRepository= ClientRepository.getInstance();
    }


    public MutableLiveData<Measurements> getMeasurementsMutableLiveData() {
        return measurementsMutableLiveData;
    }
}
