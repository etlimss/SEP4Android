package com.example.sep4android.view;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;
import com.example.sep4android.data.model.AlertValue;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.databinding.FragmentSetAlertBinding;
import com.example.sep4android.viewModel.SetAlertViewModel;

import static android.content.Context.NOTIFICATION_SERVICE;


public class SetAlertFragment extends Fragment {
    private FragmentSetAlertBinding binding;
    private double tMin, tMax, cMin, cMax, hMin, hMax;
    SetAlertViewModel setAlertViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_set_alert, container, false);
        View root= binding.getRoot();
        setAlertViewModel= new ViewModelProvider(getActivity()).get(SetAlertViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setSetAlertViewModel(setAlertViewModel);
        binding.setSetAlertFragment(this);
        initListeners(root);
        //initSetting();
        return root;
    }



    public void initListeners(View view)
    {
        ImageView imgViewSetHumidity = view.findViewById(R.id.imgViewSetHum);
        ImageView imgViewSetTemperature = view.findViewById(R.id.imgViewSetTemperature);
        ImageView imgViewSetCo2 = view.findViewById(R.id.imgViewSetCo2);
        imgViewSetTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.temperature_dialog);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                EditText tempMin = dialog.findViewById(R.id.tempMin);
                EditText tempMax = dialog.findViewById(R.id.tempMax);
                Button btnInsert = dialog.findViewById(R.id.btnInsertTemperature);

                btnInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        TemperatureAlert tmpAlert = new TemperatureAlert(1,Double.parseDouble(tempMin.getText().toString()),Double.parseDouble(tempMax.getText().toString()));
                        setAlertViewModel.addTemperatureAlert(tmpAlert);
                    }
                });
            }
        });


        imgViewSetHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.humidity_dialog);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                EditText humMin = dialog.findViewById(R.id.humMin);
                EditText humMax = dialog.findViewById(R.id.humMax);
                Button btnInsert = dialog.findViewById(R.id.btnInsertHumidity);

                btnInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        HumidityAlert tmpAlert = new HumidityAlert(1,Double.parseDouble(humMin.getText().toString()),Double.parseDouble(humMax.getText().toString()));
                        setAlertViewModel.addHumidityAlert(tmpAlert);
                    }
                });
            }
        });


        imgViewSetCo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.co2_dialog);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                EditText co2Min = dialog.findViewById(R.id.co2Min);
                EditText co2Max = dialog.findViewById(R.id.co2Max);
                Button btnInsert = dialog.findViewById(R.id.btnInsertCo2);

                btnInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Co2Alert tmpAlert = new Co2Alert(1,Double.parseDouble(co2Min.getText().toString()),Double.parseDouble(co2Max.getText().toString()));
                        setAlertViewModel.addCo2Alert(tmpAlert);
                    }
                });
            }
        });

    }
//    public void setAlert(){
//        temperatureMin= binding.TemperatureMin.getText().toString();
//        temperatureMax= binding.TemperatureMax.getText().toString();
//        cO2Min= binding.CO2Min.getText().toString();
//        cO2Max= binding.CO2Max.getText().toString();
//        humidityMin= binding.HumidityMin.getText().toString();
//        humidityMax= binding.HumidityMax.getText().toString();
//
//        if (temperatureMin!=null||!temperatureMin.equals("")){
//            tMin= Double.parseDouble(temperatureMin);
//        }
//
//        if (temperatureMax!=null||!temperatureMax.equals("")){
//            tMax= Double.parseDouble(temperatureMax);
//        }
//
//        if (cO2Min!=null||!cO2Min.equals("")){
//            cMin= Double.parseDouble(cO2Min);
//        }
//        if (cO2Max!=null||!cO2Max.equals("")){
//            cMax= Double.parseDouble(cO2Max);
//        }
//
//        if (humidityMin!=null||!humidityMin.equals("")){
//            hMin= Double.parseDouble(humidityMin);
//        }
//
//        if (humidityMax!=null||!humidityMax.equals("")){
//            hMax= Double.parseDouble(humidityMax);
//        }
//
//        aV= new AlertValue(tMin, tMax, hMin, hMax, cMin, cMax);
//
//
//        LiveData<AlertValue> alertValueLiveData= setAlertViewModel.getAlert(3);
//        alertValueLiveData.observe(this, new Observer<AlertValue>() {
//            @Override
//            public void onChanged(AlertValue alertValue) {
//                if (alertValue== null){
//                    setAlertViewModel.insertAlert(aV);
//                }else {
//                    aV.setUserId(3);
//                    setAlertViewModel.updateAlert(aV);
//                    initSetting();
//                }
//            }
//        });
//
////        initData();
////
////        compareData();
//    }
//
//    public void initSetting(){
//        LiveData<AlertValue> alertValueLiveData= setAlertViewModel.getAlert(3);
//        alertValueLiveData.observe(getViewLifecycleOwner(), new Observer<AlertValue>() {
//            @Override
//            public void onChanged(AlertValue alertValue) {
//                if (alertValue== null){
//                    Toast.makeText(getContext(), "something wrong", Toast.LENGTH_LONG).show();
//                }else {
//                    binding.TemperatureMin.setText(alertValue.getTemperatureMinStr());
//                    binding.TemperatureMax.setText(alertValue.getTemperatureMaxStr());
//                    binding.HumidityMin.setText(alertValue.getHumidityMinStr());
//                    binding.HumidityMax.setText(alertValue.getHumidityMaxStr());
//                    binding.CO2Min.setText(alertValue.getCo2MinStr());
//                    binding.CO2Max.setText(alertValue.getCo2MaxStr());
//
//                }
//            }
//        });
//    }
//
//
//    public void initData(){
//        Measurements measurements= mainViewModel.getMeasurementsMutableLiveData().getValue();
//
//        temperature= measurements.getTemperature();
//        co2= measurements.getCo2();
//        humidity=measurements.getHumidity();
//
//    }
//
//    public void compareData(){
//
//        String title= "";
//        String text= "";
//
//        if (temperature<= tMin){
//            title= "the temperature is too low";
//            text= "The value of temperature is "+temperature;
//        }
//
//        if (temperature>= tMax){
//            title= "the temperature is too high";
//            text= "The value of temperature is "+temperature;
//        }
//
//        if (humidity<= hMin){
//            title= "the humidity is too low";
//            text= "The value of humidity is "+humidity;
//        }
//
//        if (humidity>= hMax){
//            title= "the humidity is too high";
//            text= "The value of humidity is "+humidity;
//        }
//
//        if (co2<= cMin){
//            title= "the co2 is too low";
//            text= "The value of co2 is "+co2;
//        }
//
//        if (co2>= cMax){
//            title= "the co2 is too high";
//            text= "The value of co2 is "+co2;
//        }
//
//
//    }



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