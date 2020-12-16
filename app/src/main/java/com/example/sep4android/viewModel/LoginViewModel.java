package com.example.sep4android.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.data.model.User;
import com.example.sep4android.data.networking.ClientRepository;

public class LoginViewModel extends AndroidViewModel {

    private ClientRepository repo;

    public LoginViewModel(Application application) {
        super(application);
        repo = ClientRepository.getInstance();
    }

    public void loginAccount(String username, String password) {
        repo.loginAccount(username, password);
    }

    public LiveData<User> getCurrentUser() {
        return repo.getCurrentUser();
    }
}
