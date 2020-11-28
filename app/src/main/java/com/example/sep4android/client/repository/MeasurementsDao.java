package com.example.sep4android.client.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sep4android.client.model.Measurements;

import java.util.List;

@Dao
public interface MeasurementsDao {
    @Insert
    void insert(Measurements... measurements);

    @Update
    void update(Measurements... measurements);

    @Delete
    void delete(Measurements... measurements);

    @Query("Delete from Measurements")
    void deleteAllMeasurements();

    @Query("select * from Measurements order by ID DESC")
    LiveData<List<Measurements>> getAllMeasurements();

}
