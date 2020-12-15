package com.example.sep4android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sep4android.R;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.viewModel.HistroyViewModel;
import com.example.sep4android.databinding.FragmentHistroyBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistroyViewModel dashboardViewModel;
    private FragmentHistroyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(HistroyViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_histroy, container, false);
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_histroy, container, false);
        View root= binding.getRoot();

        binding.setLifecycleOwner(this);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));


        List<Measurements> measurementsList= new ArrayList<>();
        measurementsList.add(new Measurements(1,1,1));
        measurementsList.add(new Measurements(2,2,2));
        measurementsList.add(new Measurements(3,3,3));
        measurementsList.add(new Measurements(4,4,4));
        measurementsList.add(new Measurements(5,5,5));
        MeasurementsAdapter adapter= new MeasurementsAdapter(measurementsList);
        binding.recycleView.setAdapter(adapter);

        return root;
    }
}