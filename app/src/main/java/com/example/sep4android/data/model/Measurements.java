package com.example.sep4android.data.model;

public class Measurements {
    private double temperature;
    private double humidity;
    private double co2;
    private boolean light;

    public Measurements() {
    }

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

    @Override
    public String toString() {
        return "Measurements{" +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", co2=" + co2 +
                ", light=" + light +
                '}';
    }

}
