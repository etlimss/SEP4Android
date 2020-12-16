package com.example.sep4android.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sep4android.view.Login.CreateAccount_Fragment;
import com.example.sep4android.view.Login.Login_Fragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    final int pageCount = 2;
    private String tabTitles[] = new String[]{"Login","Create an Account"};
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Login_Fragment();
            case 1:
                return new CreateAccount_Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
