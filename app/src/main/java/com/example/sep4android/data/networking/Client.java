package com.example.sep4android.data.networking;

import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.data.model.User;

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

}
