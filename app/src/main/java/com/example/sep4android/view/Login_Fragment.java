package com.example.sep4android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.data.model.User;
import com.example.sep4android.view.MainActivity;
import com.example.sep4android.viewModel.LoginViewModel;

public class Login_Fragment extends Fragment {

    //LoginBinding loginBinding;
    EditText usernameField;
    EditText passwordField;
    LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        usernameField = v.findViewById(R.id.usernameText);
        passwordField = v.findViewById(R.id.passwordText);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        Button button = v.findViewById(R.id.btnLogin);
        button.setOnClickListener(v1 -> loginAccount(getView()));
        loginViewModel.getisUserLoggedIn().observe(getViewLifecycleOwner(),new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bl) {
                if (bl == false) {
                    Toast.makeText(getContext(), "Credentials are incorrect!", Toast.LENGTH_SHORT).show();
                } else {
                    intentToSystem();
                }
            }
        });
        return v;
    }


    public void loginAccount(View view){
        loginViewModel.loginAccount(usernameField.getText().toString(),passwordField.getText().toString());
    }

    public void intentToSystem(){
        Intent intent= new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}