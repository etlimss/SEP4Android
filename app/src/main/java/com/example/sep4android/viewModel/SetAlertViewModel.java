package com.example.sep4android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.data.networking.ClientRepository;
import com.example.sep4android.data.repository.AlertRepository;

import java.util.List;

public class SetAlertViewModel extends AndroidViewModel {

    private AlertRepository alertRepository;
    private ClientRepository clientRepository;


    public SetAlertViewModel(@NonNull Application application) {
        super(application);
        alertRepository= AlertRepository.getInstance(application);
        clientRepository= ClientRepository.getInstance();
    }

    /*
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
                           LOCAL DATABASE STUFF
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
    ------------------------------------------------------------------
 */
    //  Temperature Alerts/Get/Update/Insert
    // GETTING THE ALERT INFO!!!!!!!!!!!!!!!!!!!!!
    public LiveData<TemperatureAlert> getTemperatureAlert(long userId) {
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

    // ADDING ALERTS TO THE DATABASE !!!!!!!!!!!!!!!!!!!!!
    public void addTemperatureAlert(TemperatureAlert temperatureAlert)
    {
        alertRepository.addTemperatureAlert(temperatureAlert);
    }

    public void addCo2Alert(Co2Alert co2Alert)
    {
        alertRepository.addCo2Alert(co2Alert);
    }

    public void addHumidityAlert(HumidityAlert humidityAlert)
    {
        alertRepository.addHumidityAlert(humidityAlert);
    }

    // update alerts to the database

    public void updateTemperatureAlert(TemperatureAlert temperatureAlert)
    {
        alertRepository.updateTemperatureAlert(temperatureAlert);
    }

    public void updateCo2Alert(Co2Alert co2Alert)
    {
        alertRepository.updateCo2Alert(co2Alert);
    }

    public void updateHumidityAlert(HumidityAlert humidityAlert)
    {
        alertRepository.updateHumidityAlert(humidityAlert);
    }


    /* REMOVING METHODS FOR THE ALERTS!!!!!!!!!!!!!!!!!!!!!!! */
    public void removeTemperatureAlert(TemperatureAlert temperatureAlert) {
        alertRepository.removeTemperatureAlert(temperatureAlert);
    }

    public void removeHumidityAlert(HumidityAlert humidityAlert) {
        alertRepository.removeHumidityAlert(humidityAlert);
    }

    public void removeCo2Alert(Co2Alert co2Alert) {
        alertRepository.removeCo2Alert(co2Alert);
    }

    public LiveData<Measurements> getMeasurementsMutableLiveData() {
        return clientRepository.getMeasurementsMutableLiveData();
    }
}
