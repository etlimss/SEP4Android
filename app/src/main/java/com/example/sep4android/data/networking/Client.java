package com.example.sep4android.data.networking;

import com.example.sep4android.data.model.HistoryRequest;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Client {

    @GET("/current")
    Call<Measurements> getMeasurements(@Query(value="location" , encoded=true) String location);

    @POST("/login")
    Call<User> getUser(@Body User user);

    @POST("/createAccount")
    Call<Long> createAccount(@Body User user);

    @GET("/tempHistory")
    Call<List<Double>> getTempHistory(@Body HistoryRequest body);
    @GET("/humidityHistory")
    Call<List<Double>> getHumHistory(@Body HistoryRequest body);
    @GET("/co2History")
    Call<List<Double>> getCo2History(@Body HistoryRequest body);
    @GET("/lightHistory")
    Call<List<Double>> getLightHistory(@Body HistoryRequest body);

}
