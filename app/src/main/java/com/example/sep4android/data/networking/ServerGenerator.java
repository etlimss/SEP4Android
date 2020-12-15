package com.example.sep4android.data.networking;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerGenerator {
    private static Retrofit.Builder builder= new Retrofit.Builder()
            .baseUrl("https://database-sep.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit= builder.build();
    private static Client client= retrofit.create(Client.class);
    public static Client getClient(){
        return client;
    }
}
