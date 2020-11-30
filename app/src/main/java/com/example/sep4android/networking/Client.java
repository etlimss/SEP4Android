package com.example.sep4android.networking;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Client {
    @GET
    Call<User> getUser(String username, String password);

    @POST
    Call<String> setUser(String username, String password);

    @GET
    Call<Measurements> getMeasurements();


}
