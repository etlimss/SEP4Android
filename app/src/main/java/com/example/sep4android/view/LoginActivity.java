package com.example.sep4android.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.data.model.User;
import com.example.sep4android.viewModel.LoginViewModel;
import com.example.sep4android.databinding.LoginBinding;

public class LoginActivity extends AppCompatActivity {
    LoginBinding loginBinding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.login);
        loginBinding.setLoginActivity(this);
        loginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);
        loginBinding.setLifecycleOwner(this);

        loginViewModel.getCurrentUser().observe(this, user -> {
            if (user==null){
                Toast.makeText(LoginActivity.this, "something wrong", Toast.LENGTH_SHORT).show();
            }else {
                intentToSystem();
            }
        });

    }

    public void intentToSignUp(){
        Intent intent= new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void intentToSystem(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loginAccount(){
        loginViewModel.loginAccount();
    }



//    public void listenInput(){
//        loginBinding.usernameText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                loginViewModel.getUsername().setValue(s.toString());
//            }
//        });
//
//        loginBinding.passwordText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                loginViewModel.getPassword().setValue(s.toString());
//            }
//        });
//    }


}
