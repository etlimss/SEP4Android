package com.example.sep4android.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRepository {
    private static ClientRepository instance;
    private static Client client;
    private MutableLiveData<User> user;
    private MutableLiveData<Measurements> measurements;

    public static synchronized ClientRepository getInstance(){
        if (instance==null){
            instance= new ClientRepository();
            client= ServerGenerator.getClient();
        }

        return instance;
    }

    public void signUpAccount(String username, String password){

    }

    public void loginAccount(String username, String password){
        Call<User> userCall= client.getUser(username, password);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                    user.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getMeasurements(){
        Call<Measurements> call= client.getMeasurements();
        call.enqueue(new Callback<Measurements>() {
            @Override
            public void onResponse(Call<Measurements> call, Response<Measurements> response) {
                measurements.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Measurements> call, Throwable t) {

            }
        });
    }
}
