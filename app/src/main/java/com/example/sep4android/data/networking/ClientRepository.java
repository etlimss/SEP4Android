package com.example.sep4android.data.networking;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.data.model.HistoryRequest;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.data.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRepository {
    private static ClientRepository instance;
    private static Client client;
    private  MutableLiveData<User> user;
    private  MutableLiveData<Measurements> measurementsMutableLiveData;
    private MutableLiveData<List<Measurements>> listMutableLiveData;
    private List<Measurements> measurementsList;
    MutableLiveData<Boolean> isLoggedIn;
    MutableLiveData<Boolean> isAccountCreated;

    private ClientRepository() {
        measurementsMutableLiveData= new MutableLiveData<>();
        listMutableLiveData= new MutableLiveData<>();
        measurementsList= new ArrayList<>();
        listMutableLiveData.setValue(measurementsList);
        user = new MutableLiveData<>();
        client = ServerGenerator.getClient();
        isLoggedIn = new MutableLiveData<>();
        isAccountCreated = new MutableLiveData<>();
    }

    public static synchronized ClientRepository getInstance(){
        if (instance==null){
            instance = new ClientRepository();
        }
        return instance;
    }

    public LiveData<User> getCurrentUser()
    {
        return user;
    }

    public void addRandomUserData()
    {
        Log.i("Account","I WAS CALLED!");
        user.setValue(new User("Heello","world"));
    }

    public LiveData<Boolean> getisAccountCreated()
    {
        return isAccountCreated;
    }

    public LiveData<Boolean> getisLoggedIn()
    {
        return isLoggedIn;
    }

    public void signUpAccount(String username, String password){
        Client clientAPI = ServerGenerator.getClient();
        Call<Void> userCall = clientAPI.createAccount(new User(username,password));
        userCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i("Retrofit", "Account has been created!");
                    isAccountCreated.setValue(true);
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
                isAccountCreated.setValue(false);
            }
        });
    }

    public void loginAccount(String username, String password) {
        Log.i("Retrofit","I WAS CALLED!");
        Client clientAPI = ServerGenerator.getClient();
        Call<Void> call = clientAPI.getUser(new User(username,password));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i("Retrofit", "We have been successfully logged in!");
                    isLoggedIn.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
                isLoggedIn.setValue(false);
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
                        measurementsMutableLiveData.setValue(response.body());
                    }
                }
            }
            @Override
            public void onFailure(Call<Measurements> call, Throwable t) {
                Log.e("measurement", "error");
            }
        });

//        //Fake data for testing
//        Measurements m1= new Measurements(23.6, 99.9, 1000);
//        Measurements m2= new Measurements(35, 50, 2000);
//        if (location.equals("front")){
//            measurementsMutableLiveData.setValue(m1);
//        }
//
//        if (location.equals("back")){
//            measurementsMutableLiveData.setValue(m2);
//        }

    }

    public void getHistoryFromServer(HistoryRequest body){
        Call<List<Double>> tempHistory = client.getTempHistory(body);
        Call<List<Double>> humHistory = client.getHumHistory(body);
        Call<List<Double>> co2History = client.getCo2History(body);
        Call<List<Double>> lightHistory = client.getLightHistory(body);
        tempHistory.enqueue(new Callback<List<Double>>() {
            @Override
            public void onResponse(Call<List<Double>> call, Response<List<Double>> response) {
                List<Double> body = response.body();
                for (Double d: body){
                    Measurements measurements= new Measurements();
                    measurements.setTemperature(d);
                    measurementsList.add(measurements);
                }
            }

            @Override
            public void onFailure(Call<List<Double>> call, Throwable t) {
                Log.e("getHistoryFromServer", "========================");
            }
        });

        humHistory.enqueue(new Callback<List<Double>>() {
            @Override
            public void onResponse(Call<List<Double>> call, Response<List<Double>> response) {
                List<Double> body = response.body();
                for (int i=0; i<measurementsList.size();i++){
                    measurementsList.get(i).setHumidity(body.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<Double>> call, Throwable t) {
                Log.e("getHistoryFromServer", "========================");
            }
        });

        co2History.enqueue(new Callback<List<Double>>() {
            @Override
            public void onResponse(Call<List<Double>> call, Response<List<Double>> response) {
                List<Double> body = response.body();
                for (int i=0; i<measurementsList.size();i++){
                    measurementsList.get(i).setHumidity(body.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<Double>> call, Throwable t) {
                Log.e("getHistoryFromServer", "========================");
            }
        });

        lightHistory.enqueue(new Callback<List<Double>>() {
            @Override
            public void onResponse(Call<List<Double>> call, Response<List<Double>> response) {
                List<Double> body = response.body();
                for (int i=0; i<measurementsList.size();i++){
                    measurementsList.get(i).setHumidity(body.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<Double>> call, Throwable t) {
                Log.e("getHistoryFromServer", "========================");
            }
        });
    }




    public LiveData<Measurements> getMeasurementsMutableLiveData() {
        return measurementsMutableLiveData;
    }

    public LiveData<List<Measurements>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}
