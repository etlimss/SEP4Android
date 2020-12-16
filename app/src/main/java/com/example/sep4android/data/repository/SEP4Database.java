package com.example.sep4android.data.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;
import com.example.sep4android.data.model.AlertValue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities={Co2Alert.class, TemperatureAlert.class, HumidityAlert.class}, version = 1, exportSchema = false)
public abstract class SEP4Database extends RoomDatabase {
    private static SEP4Database instance;
    public static final ExecutorService dbWriteExecutor =
            Executors.newFixedThreadPool(2);


    public static synchronized SEP4Database getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), SEP4Database.class, "sep4_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract AlertValueDao alertValueDao();
}
