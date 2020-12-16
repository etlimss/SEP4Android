package com.example.sep4android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4android.data.model.User;
import com.example.sep4android.data.networking.ClientRepository;

public class SignUpVM extends AndroidViewModel {

    private ClientRepository repo;

    public SignUpVM(@NonNull Application application) {
        super(application);
        repo = ClientRepository.getInstance();
    }
    public LiveData<User> getUser() {
        return repo.getCurrentUser();
    }

    public void signUpAccount(String username, String password){
        repo.signUpAccount(username, password);
   }

}
