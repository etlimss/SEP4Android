package com.example.sep4android.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;

import com.example.sep4android.data.model.AlertValue;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.databinding.FragmentSetAlertBinding;
import com.example.sep4android.viewModel.MainViewModel;
import com.example.sep4android.viewModel.SetAlertViewModel;

import static android.content.Context.NOTIFICATION_SERVICE;


public class SetAlertFragment extends Fragment {
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

    SetAlertViewModel setAlertViewModel;

    AlertValue aV;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_set_alert, container, false);
        View root= binding.getRoot();
        mainViewModel= new ViewModelProvider(getActivity()).get(MainViewModel.class);
        binding.setLifecycleOwner(getActivity());

        setAlertViewModel= new ViewModelProvider(this).get(SetAlertViewModel.class);
        binding.setSetAlertViewModel(setAlertViewModel);
        binding.setSetAlertFragment(this);

        initSetting();
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

        if (temperatureMin!=null||!temperatureMin.equals("")){
            tMin= Double.parseDouble(temperatureMin);
        }

        if (temperatureMax!=null||!temperatureMax.equals("")){
            tMax= Double.parseDouble(temperatureMax);
        }

        if (cO2Min!=null||!cO2Min.equals("")){
            cMin= Double.parseDouble(cO2Min);
        }
        if (cO2Max!=null||!cO2Max.equals("")){
            cMax= Double.parseDouble(cO2Max);
        }

        if (humidityMin!=null||!humidityMin.equals("")){
            hMin= Double.parseDouble(humidityMin);
        }

        if (humidityMax!=null||!humidityMax.equals("")){
            hMax= Double.parseDouble(humidityMax);
        }

        aV= new AlertValue(tMin, tMax, hMin, hMax, cMin, cMax);


        LiveData<AlertValue> alertValueLiveData= setAlertViewModel.getAlert(3);
        alertValueLiveData.observe(this, new Observer<AlertValue>() {
            @Override
            public void onChanged(AlertValue alertValue) {
                if (alertValue== null){
                    setAlertViewModel.insertAlert(aV);
                }else {
                    aV.setUserId(3);
                    setAlertViewModel.updateAlert(aV);
                    initSetting();
                }
            }
        });

//        initData();
//
//        compareData();
    }

    public void initSetting(){
        LiveData<AlertValue> alertValueLiveData= setAlertViewModel.getAlert(3);
        alertValueLiveData.observe(this, new Observer<AlertValue>() {
            @Override
            public void onChanged(AlertValue alertValue) {
                if (alertValue== null){
                    Toast.makeText(getContext(), "something wrong", Toast.LENGTH_LONG).show();
                }else {
                    binding.TemperatureMin.setText(alertValue.getTemperatureMinStr());
                    binding.TemperatureMax.setText(alertValue.getTemperatureMaxStr());
                    binding.HumidityMin.setText(alertValue.getHumidityMinStr());
                    binding.HumidityMax.setText(alertValue.getHumidityMaxStr());
                    binding.CO2Min.setText(alertValue.getCo2MinStr());
                    binding.CO2Max.setText(alertValue.getCo2MaxStr());

                }
            }
        });
    }

    public void testData(){
        AlertValue a2= new AlertValue(9,9,9,9,9,9,9);
        a2.setUserId(1);
        setAlertViewModel.updateAlert(a2);
        LiveData<AlertValue> alertValueLiveData= setAlertViewModel.getAlert(1);
        alertValueLiveData.observe(this, new Observer<AlertValue>() {
            @Override
            public void onChanged(AlertValue alertValue) {
                if (alertValue== null){
                    Toast.makeText(getContext(), "something wrong", Toast.LENGTH_LONG).show();
                }else {
                    Log.e("stq", alertValue.toString());
                }
            }
        });

    }

    public void initData(){
        Measurements measurements= mainViewModel.getMeasurementsMutableLiveData().getValue();

        temperature= measurements.getTemperature();
        co2= measurements.getCo2();
        humidity=measurements.getHumidity();

    }

    public void compareData(){

        String title= "";
        String text= "";

        if (temperature<= tMin){
            title= "the temperature is too low";
            text= "The value of temperature is "+temperature;
        }

        if (temperature>= tMax){
            title= "the temperature is too high";
            text= "The value of temperature is "+temperature;
        }

        if (humidity<= hMin){
            title= "the humidity is too low";
            text= "The value of humidity is "+humidity;
        }

        if (humidity>= hMax){
            title= "the humidity is too high";
            text= "The value of humidity is "+humidity;
        }

        if (co2<= cMin){
            title= "the co2 is too low";
            text= "The value of co2 is "+co2;
        }

        if (co2>= cMax){
            title= "the co2 is too high";
            text= "The value of co2 is "+co2;
        }


    }



    public void createNotification(String title, String contentText){
        NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(getContext());

        Intent mIntent = new Intent(getContext(), MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,mIntent,0);

        builder.setContentIntent(pendingIntent);

        builder.setSmallIcon(R.drawable.ic_launcher_background);

        builder.setContentTitle(title);

        builder.setAutoCancel(true);

        builder.setContentText(contentText);

        Notification notification = builder.build();

        notificationManager.notify(0, notification);
    }
}