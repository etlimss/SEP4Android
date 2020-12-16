package com.example.sep4android.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;
import com.example.sep4android.data.model.AlertValue;


public class AlertRepository {
    private AlertValueDao alertValueDao;
    private LiveData<AlertValue> alertValueLiveData;
    private SEP4Database sep4Database;

    private static AlertRepository instance;

    public AlertRepository(Application application) {

        sep4Database= SEP4Database.getInstance(application);
        alertValueDao= sep4Database.alertValueDao();
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
    public LiveData<TemperatureAlert> getTemperatureAlert(long userId) {
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
