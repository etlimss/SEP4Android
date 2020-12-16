package com.example.sep4android.data.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sep4android.data.model.AlertValue;


@Dao
public interface AlertValueDao {

    @Insert
    void insert(AlertValue... alertValues);

    @Update
    void update(AlertValue... alertValues);

    @Delete
    void delete(AlertValue... alertValues);

    @Query("select * from AlertValue where userId like :userId ")
    LiveData<AlertValue> getAlertValue(long userId);
}
