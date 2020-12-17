package com.example.sep4android.data.model;

public class HistoryRequest {
    final String location;
    final String from;
    final String to;

    public HistoryRequest(String location, String from, String to) {
        this.location = location;
        this.from = from;
        this.to = to;
    }
}
