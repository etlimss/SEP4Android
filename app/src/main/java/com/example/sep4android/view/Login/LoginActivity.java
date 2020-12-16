package com.example.sep4android.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.sep4android.Login_Fragment;
import com.example.sep4android.R;
import com.example.sep4android.data.model.User;
import com.example.sep4android.view.MainActivity;
import com.example.sep4android.view.PagerAdapter;
import com.example.sep4android.viewModel.LoginViewModel;
import com.example.sep4android.databinding.LoginBinding;
import com.google.android.material.tabs.TabLayout;


public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ViewPager vp = findViewById(R.id.viewPager);
        PagerAdapter pA = new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pA);

        TabLayout tL = findViewById(R.id.login_createTab);
        tL.setupWithViewPager(vp);
    }
}
