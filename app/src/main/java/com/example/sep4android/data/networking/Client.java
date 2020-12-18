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
    Call<Void> getUser(@Body User user);

    @POST("/createAccount")
    Call<Void> createAccount(@Body User user);

    @GET("/tempHistory")
    Call<List<Double>> getTempHistory(@Query("location") String location, @Query("from") String from, @Query("to") String to);
    @GET("/humidityHistory")
    Call<List<Double>> getHumHistory(@Query("location") String location, @Query("from") String from, @Query("to") String to);
    @GET("/co2History")
    Call<List<Double>> getCo2History(@Query("location") String location, @Query("from") String from, @Query("to") String to);
    @GET("/lightHistory")
    Call<List<Boolean>> getLightHistory(@Query("location") String location, @Query("from") String from, @Query("to") String to);

}
