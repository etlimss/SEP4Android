package com.example.sep4android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4android.data.model.User;
import com.example.sep4android.data.networking.ClientRepository;

public class AccountViewModel extends AndroidViewModel {

    private ClientRepository clientRepository;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        clientRepository= ClientRepository.getInstance();
    }

    public void getRandomUser()
    {
        clientRepository.addRandomUserData();
    }

    public LiveData<User> getUser() {
        return clientRepository.getCurrentUser();
    }
}