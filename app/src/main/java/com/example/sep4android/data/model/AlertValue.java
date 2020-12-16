package com.example.sep4android.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AlertValue {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private double temperatureMin, temperatureMax, humidityMin, humidityMax, co2Min, co2Max;

    public AlertValue(double temperatureMin, double temperatureMax, double humidityMin, double humidityMax, double co2Min, double co2Max) {
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;

        this.co2Min = co2Min;
        this.co2Max = co2Max;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
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

    public double getCo2Min() {
        return co2Min;
    }

    public void setCo2Min(double co2Min) {
        this.co2Min = co2Min;
    }

    public double getCo2Max() {
        return co2Max;
    }

    public void setCo2Max(double co2Max) {
        this.co2Max = co2Max;
    }

    @Override
    public String toString() {
        return "AlertValue{" +
                ", temperatureMin=" + temperatureMin +
                ", temperatureMax=" + temperatureMax +
                ", humidityMin=" + humidityMin +
                ", humidityMax=" + humidityMax +
                ", co2Min=" + co2Min +
                ", co2Max=" + co2Max +
                '}';
    }
}
