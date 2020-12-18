package com.example.sep4android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.data.networking.ClientRepository;
import com.example.sep4android.data.repository.AlertRepository;

import java.util.List;

public class CurrentDataViewModel extends AndroidViewModel {

    private ClientRepository clientRepository;
    private AlertRepository alertRepository;

    public CurrentDataViewModel(@NonNull Application application) {
        super(application);
        clientRepository= ClientRepository.getInstance();
        alertRepository= AlertRepository.getInstance(application);
    }

    public LiveData<Measurements> getMeasurementsMutableLiveData() {
        return clientRepository.getMeasurementsMutableLiveData();
    }

    public void getMeasurementsFromServer(String location){
        clientRepository.getMeasurementsFromServer(location);
    }

    // GETTING THE ALERT INFO!!!!!!!!!!!!!!!!!!!!!

    public LiveData<List<TemperatureAlert>> getTempAlert(int userId)
    {
        return alertRepository.getTempAlert(userId);
    }

    public LiveData<TemperatureAlert> getTemperatureAlert(int userId) {
        return alertRepository.getTemperatureAlert(userId);
    }
    //  Temperature Alerts/Get/Update/Insert
    public LiveData<HumidityAlert> getHumidityAlert(long userId) {
        return alertRepository.getHumidityAlert(userId);
    }
    //  Temperature Alerts/Get/Update/Insert
    public LiveData<Co2Alert> getCo2Alert(long userId) {
        return alertRepository.getCo2Alert(userId);
    }


}