package com.example.sep4android.client.model;

import androidx.annotation.IdRes;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public class Measurements {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private double temperature;
    private double humidity;
    private double co2;
    private boolean light;

    //private LocalDateTime dateTime;
//    private Device device;

    public Measurements(double temperature, double humidity, double co2) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;

    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
