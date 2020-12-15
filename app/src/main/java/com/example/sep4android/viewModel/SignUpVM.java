package com.example.sep4android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.data.model.User;
import com.example.sep4android.data.networking.ClientRepository;

public class SignUpVM extends AndroidViewModel {

    private ClientRepository repo;

    private MutableLiveData<String> username= new MutableLiveData<>();
    private MutableLiveData<String> password= new MutableLiveData<>();

    public SignUpVM(@NonNull Application application) {
        super(application);

        repo = ClientRepository.getInstance();
    }

    public LiveData<User> getUser() {
        return repo.getCurrentUser();
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void signUpAccount(){
        String uName= username.getValue();
        String uPassword= password.getValue();
        repo.signUpAccount(uName, uPassword);
   }

}
