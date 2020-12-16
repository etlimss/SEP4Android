package com.example.sep4android.data.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.data.model.User;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRepository {
    private static ClientRepository instance;
    private static Client client;
    private  MutableLiveData<User> user;
    private  MutableLiveData<Measurements> measurements;

    private ClientRepository() {
        measurements= new MutableLiveData<>();
        user = new MutableLiveData<>();
        client = ServerGenerator.getClient();
    }

    public static synchronized ClientRepository getInstance(){
        if (instance==null){
            instance = new ClientRepository();
        }
        return instance;
    }

    public LiveData<User> getCurrentUser() {
        return user;
    }

    public void signUpAccount(String username, String password){
        Client clientAPI = ServerGenerator.getClient();
        Call<Long> userCall = clientAPI.createAccount(new User(username,password));
        userCall.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                if (response.isSuccessful()) {
                    Log.i("Retrofit", "Account has been created!");
                }
            }
            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void loginAccount(String username, String password) {
        Log.i("Retrofit","I WAS CALLED!");
        Client clientAPI = ServerGenerator.getClient();
        Call<User> call = clientAPI.getUser(new User(username,password));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.i("Retrofit", "We have been successfully logged in!");
                    if (response.body() != null) {
                        user.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                user = null;
                Log.i("Retrofit", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void getMeasurementsFromServer(String location){
        Client clientAPI = ServerGenerator.getClient();
        Call<Measurements> call= clientAPI.getMeasurements(location);
        call.enqueue(new Callback<Measurements>() {
            @Override
            public void onResponse(Call<Measurements> call, Response<Measurements> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("Measure", response.body().toString());
                        measurements.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Measurements> call, Throwable t) {
                Log.e("measurement", "error");
            }
        });
    }

    public LiveData<Measurements> getMeasurements() {
        return measurements;
    }
}
