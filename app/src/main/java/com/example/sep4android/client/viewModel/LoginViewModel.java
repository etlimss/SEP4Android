package com.example.sep4android.client.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.client.model.User;
import com.example.sep4android.client.repository.UserRepository;

public class LoginViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private MutableLiveData<String> username= new MutableLiveData<>();
    private MutableLiveData<String> password= new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository= UserRepository.getInstance(application);

    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public LiveData<User> loginAccount(String username, String password){

         return  userRepository.getUserLiveData(username,password);

    }


}
