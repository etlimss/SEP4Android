package com.example.sep4android.client.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.client.model.Current;
import com.example.sep4android.networking.ClientRepository;

public class CurrentDataViewModel extends AndroidViewModel {

    private ClientRepository clientRepository;

    private MutableLiveData<Current> currentMutableLiveData;


    public CurrentDataViewModel(@NonNull Application application) {
        super(application);
        clientRepository= ClientRepository.getInstance();
        currentMutableLiveData= getCurrent();
    }

    public MutableLiveData<Current> getCurrent(){
        return  clientRepository.getCurrentMutableLiveData();
    }

    public void getCurrentFromServer(){
        clientRepository.getCurrentFromServer();
    }

    public void getMeasurements(){
        clientRepository.getMeasurements();
    }

}