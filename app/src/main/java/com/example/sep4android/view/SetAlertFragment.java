package com.example.sep4android.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.databinding.FragmentSetAlertBinding;
import com.example.sep4android.viewModel.MainViewModel;
import com.example.sep4android.viewModel.SetAlertViewModel;

import java.util.Objects;

import static android.content.Context.NOTIFICATION_SERVICE;


public class SetAlertFragment extends Fragment {
    private SetAlertViewModel setAlertViewModel;
    private FragmentSetAlertBinding binding;
    private String cO2Min;
    private String cO2Max;
    private String temperatureMin;
    private String temperatureMax;
    private String humidityMin;
    private String humidityMax;


    private double temperature, co2, humidity;
    private double tMin, tMax, cMin, cMax, hMin, hMax;

    MainViewModel mainViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setAlertViewModel = new ViewModelProvider(this).get(SetAlertViewModel.class);
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_set_alert, container, false);
        binding.setSetAlertFragment(this);
        binding.setLifecycleOwner(this);
        View root= binding.getRoot();
        mainViewModel= new ViewModelProvider(getActivity()).get(MainViewModel.class);
        binding.setLifecycleOwner(getActivity());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public void setAlert(){


        temperatureMin= binding.TemperatureMin.getText().toString();
        temperatureMax= binding.TemperatureMax.getText().toString();
        cO2Min= binding.CO2Min.getText().toString();
        cO2Max= binding.CO2Max.getText().toString();
        humidityMin= binding.HumidityMin.getText().toString();
        humidityMax= binding.HumidityMax.getText().toString();

        tMin= Double.parseDouble(temperatureMin);
        tMax= Double.parseDouble(temperatureMax);
//        cMin= Double.parseDouble(cO2Min);
//        cMax= Double.parseDouble(cO2Max);
//        hMin= Double.parseDouble(humidityMin);
//        hMax= Double.parseDouble(humidityMax);

        initData();

        compareData();
    }

    public void initData(){
        Measurements measurements= mainViewModel.getMeasurementsMutableLiveData().getValue();

        temperature= measurements.getTemperature();
        co2= measurements.getCo2();
        humidity=measurements.getHumidity();

    }

    public void compareData(){


        if (temperature<=tMin||temperature>=tMax){
            String s1= String.valueOf(tMin);
            String s2= String.valueOf(tMax);
            createNotification(s1+"   "+s2);
        }

//        if (c<=cMin||c>=cMax){
//
//        }
//
//        if (h<=tMin||h>=hMax){
//
//        }

    }



    public void createNotification(String contentText){
        NotificationManager notificationManager = (NotificationManager) Objects.requireNonNull(getActivity()).getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(getContext());

        Intent mIntent = new Intent(getContext(), MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,mIntent,0);

        builder.setContentIntent(pendingIntent);

        builder.setSmallIcon(R.drawable.ic_launcher_background);

        builder.setContentTitle("标题");

        builder.setAutoCancel(true);

        builder.setContentText(contentText);

        Notification notification = builder.build();

        notificationManager.notify(0, notification);
    }
}