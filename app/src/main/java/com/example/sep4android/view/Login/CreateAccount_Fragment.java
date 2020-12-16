package com.example.sep4android.view.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.viewModel.SignUpVM;

public class CreateAccount_Fragment extends Fragment {

    SignUpVM signUpVM;
    EditText usernameField;
    EditText passwordField;
    Button btnCreateAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_create_account, container, false);

        signUpVM = new ViewModelProvider(this).get(SignUpVM.class);
        usernameField = (EditText)v.findViewById(R.id.usernameText);
        passwordField = (EditText)v.findViewById(R.id.passwordText);

        Button button = (Button) v.findViewById(R.id.btnCreateAccount);
        button.setOnClickListener(v1 -> signUp(getView()));
        // Inflate the layout for this fragment
        return v;
    }

    public void signUp(View view)
    {
        signUpVM.signUpAccount(usernameField.getText().toString(),passwordField.getText().toString());
    }
}

