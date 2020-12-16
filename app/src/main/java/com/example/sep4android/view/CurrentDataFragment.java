package com.example.sep4android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.databinding.FragmentCurrentdataBinding;
import com.example.sep4android.viewModel.CurrentDataViewModel;
import com.example.sep4android.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class CurrentDataFragment extends Fragment {

    CurrentDataViewModel currentDataViewModel;
    FragmentCurrentdataBinding binding;
    Spinner location;
    List<String> currents;
    ArrayAdapter<String>adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currentdata, container, false);
        currentDataViewModel = new ViewModelProvider(this).get(CurrentDataViewModel.class);
        View root= binding.getRoot();
        binding.setCurrentDataViewModel(currentDataViewModel);
        binding.setLifecycleOwner(getActivity());
        location = (Spinner)root.findViewById(R.id.location);
        initSpinner();
        return root;
    }

    public void initSpinner(){

        currents = new ArrayList<>();
        currents.add("front");
        currents.add("back");

        adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,currents);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter);
        location.setSelection(0);
        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "The selected location is " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
                currentDataViewModel.getMeasurementsFromServer(adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

}