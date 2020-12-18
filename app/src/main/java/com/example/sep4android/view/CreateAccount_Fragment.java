package com.example.sep4android.view;

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
        usernameField = v.findViewById(R.id.usernameText);
        passwordField = v.findViewById(R.id.passwordText);

        btnCreateAccount = v.findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(v1 -> signUp(getView()));
        signUpVM.getisAccountCreated().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean == true)
                {
                    Toast.makeText(getContext(), "Account was created!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    public void signUp(View view)
    {
        signUpVM.signUpAccount(usernameField.getText().toString(),passwordField.getText().toString());
    }
}

