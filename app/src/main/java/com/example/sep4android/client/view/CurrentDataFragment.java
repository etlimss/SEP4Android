package com.example.sep4android.client.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.client.model.Current;
import com.example.sep4android.client.viewModel.CurrentDataViewModel;
import com.example.sep4android.databinding.FragmentCurrentdataBinding;

import java.util.ArrayList;
import java.util.List;

public class CurrentDataFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private CurrentDataViewModel currentDataViewModel;
    private FragmentCurrentdataBinding binding;
    private Spinner spinner;
    private ArrayAdapter<String>adapter;
    private List<String> currents;
//    private List<String> currents ;



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
        currentDataViewModel.getMeasurements();
        spinner = (Spinner)root.findViewById(R.id.location);
        initSpinner();



        return root;
    }

    public void initSpinner(){

        currents = new ArrayList<String>();
        currents.add("kamjakata");
        currents.add("Student Villiage");

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,currents);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}