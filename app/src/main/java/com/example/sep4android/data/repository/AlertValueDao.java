package com.example.sep4android.data.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;
import com.example.sep4android.data.model.AlertValue;


@Dao
public interface AlertValueDao {

    @Insert
    void insertTemperatureAlert(TemperatureAlert temperatureAlert);

    @Insert
    void insertHumidityAlert(HumidityAlert humidityAlert);

    @Insert
    void insertCo2Alert(Co2Alert co2Alert);

    @Update
    void updateTemperatureAlert(TemperatureAlert temperatureAlert);

    @Update
    void updateCo2Alert(Co2Alert co2Alert);

    @Update
    void updateHumidityAlert(HumidityAlert humidityAlert);


    @Delete
    void deleteTemperatureAlert(TemperatureAlert temperatureAlert);

    @Delete
    void deleteCo2Alert(Co2Alert co2Alert);

    @Delete
    void deleteHumidityAlert(HumidityAlert humidityAlert);

    @Query("select * from Co2Alert where userId = :userId ")
    LiveData<Co2Alert> getCo2AlertValue(long userId);

    @Query("select * from TemperatureAlert where userId = :userId ")
    LiveData<TemperatureAlert> getTemperatureAlertValue(long userId);

    @Query("select * from HumidityAlert where userId = :userId ")
    LiveData<HumidityAlert> getHumidityAlertValue(long userId);

}
