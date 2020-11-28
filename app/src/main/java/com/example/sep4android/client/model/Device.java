package com.example.sep4android.client.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Device {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
}
