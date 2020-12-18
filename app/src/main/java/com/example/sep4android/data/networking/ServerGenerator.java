package com.example.sep4android.data.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerGenerator {
    private static Retrofit.Builder builder= new Retrofit.Builder()
            .baseUrl("http://192.168.87.103:8080/")
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit= builder.build();
    private static Client client= retrofit.create(Client.class);
    public static Client getClient(){
        return client;
    }
}
