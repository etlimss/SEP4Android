package com.example.sep4android.data.AlertModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Co2Alert")
public class Co2Alert {
    @PrimaryKey
    public int userId;
    public double co2Min,co2Max;

    public Co2Alert(int userId, double co2Min, double co2Max) {
        this.userId = userId;
        this.co2Min = co2Min;
        this.co2Max = co2Max;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return "Co2Alert{" +
                ", userId=" + userId +
                ", co2Min=" + co2Min +
                ", co2Max=" + co2Max +
                '}';
    }
}
