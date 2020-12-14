package com.example.sep4android.client.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4android.R;
import com.example.sep4android.client.viewModel.SetAlertViewModel;
import com.example.sep4android.databinding.FragmentSetAlertBinding;


public class SetAlertFragment extends Fragment {
    private SetAlertViewModel setAlertViewModel;
    private FragmentSetAlertBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setAlertViewModel = new ViewModelProvider(this).get(SetAlertViewModel.class);
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_set_alert, container, false);
        View root= binding.getRoot();


        return root;
    }



}