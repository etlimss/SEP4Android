package com.example.sep4android.client.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sep4android.client.model.Measurements;

@Database(entities = {Measurements.class}, version = 1)
public abstract class MeasurementsDatabase extends RoomDatabase {
    private static MeasurementsDatabase instance;

    public static synchronized MeasurementsDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), MeasurementsDatabase.class, "measurements_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }

    public abstract MeasurementsDao measurementsDao();
}
