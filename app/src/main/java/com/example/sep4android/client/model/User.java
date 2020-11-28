package com.example.sep4android.client.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Account")
public class User {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String username;
    private String password;
}
