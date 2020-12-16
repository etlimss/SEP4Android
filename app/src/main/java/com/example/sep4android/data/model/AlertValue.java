package com.example.sep4android.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class AlertValue {
    @PrimaryKey(autoGenerate = true)
    private long userId;
    private double temperatureMin, temperatureMax, humidityMin, humidityMax, co2Min, co2Max;

    private String temperatureMinStr, temperatureMaxStr, humidityMinStr, humidityMaxStr, co2MinStr, co2MaxStr;

    public AlertValue() {
    }


    @Ignore
    public AlertValue(double temperatureMin, double temperatureMax, double humidityMin, double humidityMax, double co2Min, double co2Max) {
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.humidityMin = humidityMin;
        this.humidityMax = humidityMax;
        this.co2Min = co2Min;
        this.co2Max = co2Max;
        initStr();
    }



    @Ignore
    public AlertValue(long userId, double temperatureMin, double temperatureMax, double humidityMin, double humidityMax, double co2Min, double co2Max) {
        this.userId = userId;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.humidityMin = humidityMin;
        this.humidityMax = humidityMax;
        this.co2Min = co2Min;
        this.co2Max = co2Max;
        initStr();
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

    public long getUserId() {
        return userId;
    }

    public void initStr(){
        temperatureMinStr= String.valueOf(this.temperatureMin);
        temperatureMaxStr= String.valueOf(this.temperatureMax);
        humidityMinStr= String.valueOf(humidityMin);
        humidityMaxStr= String.valueOf(humidityMax);
        co2MinStr= String.valueOf(co2Min);
        co2MaxStr= String.valueOf(co2Max);
    }

    public String getTemperatureMinStr() {
        return temperatureMinStr;
    }

    public String getTemperatureMaxStr() {
        return temperatureMaxStr;
    }

    public String getHumidityMinStr() {
        return humidityMinStr;
    }

    public String getHumidityMaxStr() {
        return humidityMaxStr;
    }

    public String getCo2MinStr() {
        return co2MinStr;
    }

    public String getCo2MaxStr() {
        return co2MaxStr;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTemperatureMinStr(String temperatureMinStr) {
        this.temperatureMinStr = temperatureMinStr;
    }

    public void setTemperatureMaxStr(String temperatureMaxStr) {
        this.temperatureMaxStr = temperatureMaxStr;
    }

    public void setHumidityMinStr(String humidityMinStr) {
        this.humidityMinStr = humidityMinStr;
    }

    public void setHumidityMaxStr(String humidityMaxStr) {
        this.humidityMaxStr = humidityMaxStr;
    }

    public void setCo2MinStr(String co2MinStr) {
        this.co2MinStr = co2MinStr;
    }

    public void setCo2MaxStr(String co2MaxStr) {
        this.co2MaxStr = co2MaxStr;
    }

    @Override
    public String toString() {
        return "AlertValue{" +
                "userId=" + userId +
                ", temperatureMin=" + temperatureMin +
                ", temperatureMax=" + temperatureMax +
                ", humidityMin=" + humidityMin +
                ", humidityMax=" + humidityMax +
                ", co2Min=" + co2Min +
                ", co2Max=" + co2Max +
                '}';
    }
}
