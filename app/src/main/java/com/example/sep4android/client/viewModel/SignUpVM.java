package com.example.sep4android.client.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.model.User;
import com.example.sep4android.client.repository.UserRepository;

import java.util.List;

public class SignUpVM extends AndroidViewModel {

    private MutableLiveData<User> user= new MutableLiveData<>();
    private UserRepository userRepository;

    private MutableLiveData<String> username= new MutableLiveData<>();
    private MutableLiveData<String> password= new MutableLiveData<>();

    public MutableLiveData<String> oneUser= new MutableLiveData<>();

    public SignUpVM(@NonNull Application application) {
        super(application);
        userRepository= UserRepository.getInstance(application);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public LiveData<List<User>> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public void signUpAccount(){
        String uName= username.getValue();
        String uPassword= password.getValue();
        User u= new User(uName, uPassword);
        userRepository.insert(u);
   }
}
