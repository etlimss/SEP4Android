package com.example.sep4android.client.view;

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
import android.view.View;
import android.widget.Toast;

import com.example.sep4android.R;
import com.example.sep4android.client.model.User;
import com.example.sep4android.client.viewModel.MainViewModel;
import com.example.sep4android.client.viewModel.SignUpVM;
import com.example.sep4android.databinding.ActivityMainBinding;
import com.example.sep4android.databinding.SignUpBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        signUpBinding.setSignUpVM(signUpVM);
        signUpBinding.setLifecycleOwner(this);

        signUpVM.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users.isEmpty()){
                    signUpBinding.textView4.setText("user data will be shown here");
                }else {
                    System.out.println(users.get(0));
                    signUpVM.oneUser.setValue(users.get(0).toString());
                }

            }
        });

        listenInput();

    }

    public void intentToLogin(){
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void listenInput(){
        signUpBinding.usernameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                signUpVM.getUsername().setValue(s.toString());
            }
        });

        signUpBinding.passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                signUpVM.getPassword().setValue(s.toString());
            }
        });
    }
}