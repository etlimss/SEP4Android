package com.example.sep4android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.viewModel.CurrentDataViewModel;

public class CurrentDataFragment extends Fragment {

    private CurrentDataViewModel currentDataViewModel;

    TextView temperatureData;
    TextView humidityData;
    TextView co2Data;
    TextView lightData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        currentDataViewModel = new ViewModelProvider(this).get(CurrentDataViewModel.class);
        View v = inflater.inflate(R.layout.fragment_currentdata, container, false);
        temperatureData = v.findViewById(R.id.temperatureData);
        humidityData = v.findViewById(R.id.humidityData);
        co2Data = v.findViewById(R.id.co2Data);
        lightData = v.findViewById(R.id.lightData);
        currentDataViewModel.getMeasurementsFromServer("front");
        currentDataViewModel.getMeasurementsMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Measurements>() {
            @Override
            public void onChanged(@Nullable Measurements measurements) {

                temperatureData.setText(String.valueOf(measurements.getTemperature()));
                humidityData.setText(String.valueOf(measurements.getHumidity()));
                co2Data.setText(String.valueOf(measurements.getCo2()));
                lightData.setText("It is fine");
            }
        });
        return v;
    }

}