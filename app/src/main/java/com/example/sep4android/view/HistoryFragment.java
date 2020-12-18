package com.example.sep4android.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4android.R;
import com.example.sep4android.data.model.HistoryRequest;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.viewModel.HistroyViewModel;
import com.example.sep4android.databinding.FragmentHistroyBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private HistroyViewModel histroyViewModel;
    private FragmentHistroyBinding binding;

    Spinner locationInHistory;
    List<String> chooseLocation;
    ArrayAdapter<String> adapter;
    MeasurementsAdapter measurementsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        histroyViewModel = new ViewModelProvider(this).get(HistroyViewModel.class);
//      View root = inflater.inflate(R.layout.fragment_histroy, container, false);
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_histroy, container, false);
        View root= binding.getRoot();

        binding.setLifecycleOwner(this);
        histroyViewModel.getHistoryFromServer("front", "2020-12-16", "2020-12-17");
        RecyclerView recyclerView= binding.recycleView;
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
        histroyViewModel.getListMutableLiveData().observe(getActivity(), new Observer<List<Measurements>>() {
            @Override
            public void onChanged(List<Measurements> measurements) {
                measurementsAdapter= new MeasurementsAdapter(measurements);
                recyclerView.setAdapter(measurementsAdapter);
            }
        });




        locationInHistory = (Spinner)root.findViewById(R.id.locationInHistory);
        initSpinner();

        return root;
    }

    public void initSpinner(){

        chooseLocation = new ArrayList<>();
        chooseLocation.add("front");
        chooseLocation.add("back");

        adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,chooseLocation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationInHistory.setAdapter(adapter);
        locationInHistory.setSelection(0);
        locationInHistory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "The selected location is " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}