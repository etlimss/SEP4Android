package com.example.sep4android.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRepository {
    private static ClientRepository instance;
    private static Client client;
    private  MutableLiveData<User> user;
    private  MutableLiveData<Measurements> measurements;



    private ClientRepository(){

        measurements= new MutableLiveData<>();
    }

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
