package com.example.sep4android.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;

import java.util.List;


public class AlertRepository {
    private SEP4Database sep4Database;
    private MutableLiveData<Co2Alert> co2Alert;
    private MutableLiveData<HumidityAlert> humidityAlert;
    private MutableLiveData<TemperatureAlert> temperatureAlert;

    private static AlertRepository instance;

    public AlertRepository(Application application) {

        co2Alert = new MutableLiveData<>();
        temperatureAlert = new MutableLiveData<>();
        humidityAlert = new MutableLiveData<>();
        sep4Database= SEP4Database.getInstance(application);
    }

    public static synchronized AlertRepository getInstance(Application application){
        if (instance==null){
            instance= new AlertRepository(application);
        }
        return instance;
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
    public LiveData<TemperatureAlert> getTemperatureAlert(int userId) {
        return sep4Database.alertValueDao().getTemperatureAlertValue(userId);
    }
    //  Temperature Alerts/Get/Update/Insert
    public LiveData<HumidityAlert> getHumidityAlert(long userId) {
        return sep4Database.alertValueDao().getHumidityAlertValue(userId);
    }
    //  Temperature Alerts/Get/Update/Insert
    public LiveData<Co2Alert> getCo2Alert(long userId) {
        return sep4Database.alertValueDao().getCo2AlertValue(userId);
    }
    public LiveData<List<TemperatureAlert>> getTempAlert(int userId)
    {
        return sep4Database.alertValueDao().getTempAlert(userId);
    }

    // ADDING ALERTS TO THE DATABASE !!!!!!!!!!!!!!!!!!!!!
    public void addTemperatureAlert(TemperatureAlert temperatureAlert)
    {
        sep4Database.dbWriteExecutor.execute(() -> sep4Database.alertValueDao().insertTemperatureAlert(temperatureAlert));
    }

    public void addCo2Alert(Co2Alert co2Alert)
    {
        sep4Database.dbWriteExecutor.execute(() -> sep4Database.alertValueDao().insertCo2Alert(co2Alert));
    }

    public void addHumidityAlert(HumidityAlert humidityAlert)
    {
        sep4Database.dbWriteExecutor.execute(() -> sep4Database.alertValueDao().insertHumidityAlert(humidityAlert));
    }

    // update alerts to the database

    public void updateTemperatureAlert(TemperatureAlert temperatureAlert)
    {
        sep4Database.dbWriteExecutor.execute(() -> sep4Database.alertValueDao().updateTemperatureAlert(temperatureAlert));
    }

    public void updateCo2Alert(Co2Alert co2Alert)
    {
        sep4Database.dbWriteExecutor.execute(() -> sep4Database.alertValueDao().updateCo2Alert(co2Alert));
    }

    public void updateHumidityAlert(HumidityAlert humidityAlert)
    {
        sep4Database.dbWriteExecutor.execute(() -> sep4Database.alertValueDao().updateHumidityAlert(humidityAlert));
    }


    /* REMOVING METHODS FOR THE ALERTS!!!!!!!!!!!!!!!!!!!!!!! */
    public void removeTemperatureAlert(TemperatureAlert temperatureAlert) {
        SEP4Database.dbWriteExecutor.execute(() -> sep4Database.alertValueDao().deleteTemperatureAlert(temperatureAlert));
    }

    public void removeHumidityAlert(HumidityAlert humidityAlert) {
        SEP4Database.dbWriteExecutor.execute(() -> sep4Database.alertValueDao().deleteHumidityAlert(humidityAlert));
    }

    public void removeCo2Alert(Co2Alert co2Alert) {
        SEP4Database.dbWriteExecutor.execute(() -> sep4Database.alertValueDao().deleteCo2Alert(co2Alert));
    }
}
