package com.example.sep4android.client.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.networking.ClientRepository;

public class CurrentDataViewModel extends AndroidViewModel {

    private ClientRepository clientRepository;

    public CurrentDataViewModel(@NonNull Application application) {
        super(application);
        clientRepository= ClientRepository.getInstance();
    }

    public MutableLiveData<Measurements> getMeasurementsMutableLiveData() {
        return clientRepository.getMeasurements();
    }

    public void getMeasurementsFromServer(String location){
        clientRepository.getMeasurementsFromServer(location);
    }

}