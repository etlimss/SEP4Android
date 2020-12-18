package com.example.sep4android.data.AlertModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "HumidityAlert")
public class HumidityAlert {
    @PrimaryKey
    private int userId;
    private double humidityMin,humidityMax;

    public HumidityAlert(int userId, double humidityMin, double humidityMax) {
        this.userId = userId;
        this.humidityMin = humidityMin;
        this.humidityMax = humidityMax;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(double humidityMin) {
        this.humidityMin = humidityMin;
    }

    public double getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(double humidityMax) {
        this.humidityMax = humidityMax;
    }

    @Override
    public String toString() {
        return "HumidityAlert{" +
                ", userId=" + userId +
                ", humidityMin=" + humidityMin +
                ", humidityMax=" + humidityMax +
                '}';
    }
}
