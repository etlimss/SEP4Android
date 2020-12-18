package com.example.sep4android.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.databinding.FragmentCurrentdataBinding;
import com.example.sep4android.viewModel.CurrentDataViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        location = root.findViewById(R.id.location);
        currentDataViewModel.getMeasurementsMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Measurements>() {
            @Override
            public void onChanged(Measurements measurements) {
                alertForTemperature(measurements);
                alertForHumidity(measurements);
                alertForCo2(measurements);
            }
        });
        initSpinner();
        return root;
    }

    public void alertForTemperature(Measurements measurements)
    {
        currentDataViewModel.getTemperatureAlert(1).observe(getViewLifecycleOwner(), new Observer<TemperatureAlert>() {
            @Override
            public void onChanged(TemperatureAlert temperatureAlert) {
                if(temperatureAlert != null) {
                    if (temperatureAlert.getTemperatureMax() < measurements.getTemperature()) {
                        createTemperatureNotification("Temperature Notification", "The temperature is too high! " + measurements.getTemperature(), 5);
                    } else {
                        createTemperatureNotification("Temperature Notification", "The temperature is too low!"  + measurements.getTemperature(), 6);
                    }
                }
            }
        });
    }

    public void alertForHumidity(Measurements measurements)
    {
        currentDataViewModel.getHumidityAlert(1).observe(getViewLifecycleOwner(), new Observer<HumidityAlert>() {
            @Override
            public void onChanged(HumidityAlert humidityAlert) {
                if(humidityAlert != null) {
                    if (humidityAlert.getHumidityMax() < measurements.getHumidity()) {
                        createHumidityNotification("Humidity Notification", "The humidity is too high! " + measurements.getHumidity(), 7);
                    } else {
                        createHumidityNotification("Humidity Notification", "The humidity is too low! " + measurements.getHumidity(), 8);
                    }
                }
            }
        });
    }

    public void alertForCo2(Measurements measurements)
    {
        currentDataViewModel.getCo2Alert(1).observe(getViewLifecycleOwner(), new Observer<Co2Alert>() {
            @Override
            public void onChanged(Co2Alert co2Alert) {
                if(co2Alert != null) {
                    if (co2Alert.getCo2Max() < measurements.getCo2()) {
                        createCo2Notification("Co2 level Notification", "The co2 level is too high! " + measurements.getCo2(), 9);
                    } else {
                        createCo2Notification("Co2 level Notification", "The co2 level is too low! " + measurements.getCo2(), 10);
                    }
                }
            }
        });
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

    public void createTemperatureNotification(String alertTitle,String alertMessage,int id){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=
                    new NotificationChannel("notifTemp","Temperature Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),"notifTemp")
                .setContentTitle(alertTitle)
                .setSmallIcon(R.drawable.temperature)
                .setAutoCancel(true)
                .setContentText(alertMessage);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
        managerCompat.notify(id,builder.build());
    }

    public void createHumidityNotification(String alertTitle,String alertMessage,int id){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=
                    new NotificationChannel("notifHum","Humidity Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(),"notifHum")
                .setContentTitle(alertTitle)
                .setSmallIcon(R.drawable.humidity)
                .setAutoCancel(true)
                .setContentText(alertMessage);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
        managerCompat.notify(id,builder.build());
    }

    public void createCo2Notification(String alertTitle,String alertMessage,int id){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=
                    new NotificationChannel("notifCo2","Co2 Notification", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(),"notifCo2")
                .setContentTitle(alertTitle)
                .setSmallIcon(R.drawable.co2)
                .setAutoCancel(true)
                .setContentText(alertMessage);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
        managerCompat.notify(id,builder.build());
    }
}