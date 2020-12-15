package com.example.sep4android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.sep4android.R;
import com.example.sep4android.data.model.User;
import com.example.sep4android.viewModel.SignUpVM;
import com.example.sep4android.databinding.ActivityMainBinding;
import com.example.sep4android.databinding.SignUpBinding;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    SignUpBinding signUpBinding;
    SignUpVM signUpVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signUpBinding= DataBindingUtil.setContentView(this, R.layout.sign_up);

        signUpVM= new ViewModelProvider(this).get(SignUpVM.class);

        signUpBinding.setSignUpActivity(this);
        signUpBinding.setViewmodel(signUpVM);

        signUpBinding.signUp.setOnClickListener(v -> {
            signUpVM.signUpAccount();
        });

        signUpVM.getUser().observe(this, v -> {
            if(v != null)
                intentToLogin();
        });

        signUpBinding.setLifecycleOwner(this);
    }

    public void intentToLogin(){
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}