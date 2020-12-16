package com.example.sep4android.view.Login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.sep4android.R;
import com.example.sep4android.view.PagerAdapter;
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
