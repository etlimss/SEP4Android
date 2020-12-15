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

    private MutableLiveData<String> username= new MutableLiveData<>();
    private MutableLiveData<String> password= new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repo = ClientRepository.getInstance();
    }

    public LoginViewModel(@NonNull Application application, ClientRepository repo) {
        super(application);
        this.repo = repo;
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void loginAccount() {
        repo.loginAccount(username.getValue(), password.getValue());
    }

    public LiveData<User> getCurrentUser() {
        return repo.getCurrentUser();
    }
}
