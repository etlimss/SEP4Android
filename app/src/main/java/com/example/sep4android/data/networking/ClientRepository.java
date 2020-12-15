package com.example.sep4android.data.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.data.model.User;

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
        final User u = new User(username, password);

        Call<Long> userCall = client.createAccount(u);
        userCall.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                user.setValue(response.body() != 400 ? u : null);
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                user.setValue(null);
                throw new RuntimeException(t);
            }
        });

    }

    public void loginAccount(String username, String password) {
        final User u = new User(username, password);

        Call<Boolean> userCall= client.getUser(u);
        userCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                user.setValue(response.isSuccessful() ? u : null);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                user.setValue(null);

                throw new RuntimeException(t);
            }
        });
    }

    public void getMeasurementsFromServer(String location){
        Call<Measurements> call= client.getMeasurements(location);
        call.enqueue(new Callback<Measurements>() {
            @Override
            public void onResponse(Call<Measurements> call, Response<Measurements> response) {
                measurements.setValue(response.body());
                Log.e("measurement", response.body().toString());
            }

            @Override
            public void onFailure(Call<Measurements> call, Throwable t) {
                Log.e("measurement", "error");
            }
        });
    }

    public MutableLiveData<Measurements> getMeasurements() {
        return measurements;
    }
}
