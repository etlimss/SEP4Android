package com.example.sep4android.client.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.sep4android.client.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

    @Query("Delete from Account")
    void deleteAllUsers();

    @Query("select * from Account order by ID DESC")
    LiveData<List<User>> getAllUsers();

    @Query("select * from Account where username like :username and password like :password")
    LiveData<User> getUser(String username, String password);
}
