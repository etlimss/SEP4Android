package com.example.sep4android.data.AlertModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TemperatureAlert")
public class TemperatureAlert {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private double temperatureMin,temperatureMax;

    public TemperatureAlert(int userId, double temperatureMin, double temperatureMax) {
        this.userId = userId;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "TemperatureAlert{" +
                "id=" + id +
                ", userId=" + userId +
                ", temperatureMin=" + temperatureMin +
                ", temperatureMax=" + temperatureMax +
                '}';
    }
}
