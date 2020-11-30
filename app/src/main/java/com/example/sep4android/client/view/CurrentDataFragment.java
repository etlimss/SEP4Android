package com.example.sep4android.client.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.client.viewModel.CurrentDataViewModel;
import com.example.sep4android.databinding.FragmentCurrentdataBinding;

public class CurrentDataFragment extends Fragment {

    private CurrentDataViewModel currentDataViewModel;
    private FragmentCurrentdataBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        currentDataViewModel =
                new ViewModelProvider(this).get(CurrentDataViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_currentdata, container, false);
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_currentdata, container, false);
        View root= binding.getRoot();
        binding.setCurrentDataViewModel(currentDataViewModel);
        binding.setLifecycleOwner(this);
        currentDataViewModel.getCurrentFromServer();
        return root;
    }

}