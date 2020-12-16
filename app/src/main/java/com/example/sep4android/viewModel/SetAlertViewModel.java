package com.example.sep4android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;
import com.example.sep4android.data.repository.AlertRepository;

public class SetAlertViewModel extends AndroidViewModel {

    private AlertRepository alertRepository;


    public SetAlertViewModel(@NonNull Application application) {
        super(application);
        alertRepository= AlertRepository.getInstance(application);
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
}
