package com.example.sep4android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        histroyViewModel = new ViewModelProvider(this).get(HistroyViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_histroy, container, false);
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_histroy, container, false);
        View root= binding.getRoot();

        binding.setLifecycleOwner(this);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));

        //History API doesn't work now
//        HistoryRequest historyRequest= new HistoryRequest("front", "2020-12-16", "2020-12-17");
//        histroyViewModel.getHistoryFromServer(historyRequest);
//        MeasurementsAdapter adapter= new MeasurementsAdapter(histroyViewModel.getListMutableLiveData().getValue());

        List<Measurements> measurements= new ArrayList<>();
        measurements.add(new Measurements(1,1,1));
        measurements.add(new Measurements(2,2,2));
        measurements.add(new Measurements(3,3,3));
        measurements.add(new Measurements(4,4,4));
        measurements.add(new Measurements(5,5,5));
        MeasurementsAdapter adapter= new MeasurementsAdapter(measurements);
        binding.recycleView.setAdapter(adapter);

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