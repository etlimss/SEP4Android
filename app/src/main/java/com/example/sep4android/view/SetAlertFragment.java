package com.example.sep4android.view;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private double temperature, humidity,co2;
    private double tMin, tMax, cMin, cMax, hMin, hMax;
    Dialog tempDialog, humDialog, co2Diglog;
    EditText tempMin, tempMax, humMin, humMax, co2Min, co2Max;
    Button btnTemp, btnHum, btnCo2;
    SetAlertViewModel setAlertViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_set_alert, container, false);
        View root= binding.getRoot();
        setAlertViewModel= new ViewModelProvider(getActivity()).get(SetAlertViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setSetAlertViewModel(setAlertViewModel);
        binding.setSetAlertFragment(this);
        initDialog();
        initListeners(root);
        initData();
        return root;
    }


    public void initDialog(){
        int width= WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;

        // temperature dialog
        tempDialog= new Dialog(getContext());
        tempDialog.setContentView(R.layout.temperature_dialog);

        tempDialog.getWindow().setLayout(width,height);

        tempMin = tempDialog.findViewById(R.id.tempMin);
        tempMax = tempDialog.findViewById(R.id.tempMax);
        btnTemp = tempDialog.findViewById(R.id.btnInsertTemperature);

        //humidity dialog
        humDialog = new Dialog(getContext());
        humDialog.setContentView(R.layout.humidity_dialog);
        humDialog.getWindow().setLayout(width,height);

        humMin = humDialog.findViewById(R.id.humMin);
        humMax = humDialog.findViewById(R.id.humMax);
        btnHum = humDialog.findViewById(R.id.btnInsertHumidity);
        //CO2 dialog
        co2Diglog= new Dialog(getContext());
        co2Diglog.setContentView(R.layout.co2_dialog);
        co2Diglog.getWindow().setLayout(width,height);

        co2Min = co2Diglog.findViewById(R.id.co2Min);
        co2Max = co2Diglog.findViewById(R.id.co2Max);
        btnCo2 = co2Diglog.findViewById(R.id.btnInsertCo2);
    }


    public void initListeners(View view)
    {
        ImageView imgViewSetHumidity = view.findViewById(R.id.imgViewSetHum);
        ImageView imgViewSetTemperature = view.findViewById(R.id.imgViewSetTemperature);
        ImageView imgViewSetCo2 = view.findViewById(R.id.imgViewSetCo2);
        imgViewSetTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               tempDialog.show();

                btnTemp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tempDialog.dismiss();
                        TemperatureAlert tmpAlert = new TemperatureAlert(1,Double.parseDouble(tempMin.getText().toString()),Double.parseDouble(tempMax.getText().toString()));
                        LiveData<TemperatureAlert> temperatureAlert = setAlertViewModel.getTemperatureAlert(1);
                        temperatureAlert.observe(getActivity(), new Observer<TemperatureAlert>() {
                            @Override
                            public void onChanged(TemperatureAlert temperatureAlert) {
                                if (temperatureAlert==null){
                                    setAlertViewModel.addTemperatureAlert(tmpAlert);
                                }else {
                                    setAlertViewModel.updateTemperatureAlert(tmpAlert);
                                }
                            }
                        });


                    }
                });
            }
        });


        imgViewSetHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                humDialog.show();

                btnHum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        humDialog.dismiss();
                        HumidityAlert tmpAlert = new HumidityAlert(1,Double.parseDouble(humMin.getText().toString()),Double.parseDouble(humMax.getText().toString()));
                        setAlertViewModel.addHumidityAlert(tmpAlert);
                    }
                });
            }
        });


        imgViewSetCo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               co2Diglog.show();

                btnCo2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        co2Diglog.dismiss();
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

    //get value form the database by userId
    public void initSetting(){
        LiveData<TemperatureAlert> temperatureAlert = setAlertViewModel.getTemperatureAlert(1);
        if (temperatureAlert!=null){
            temperatureAlert.observe(this, new Observer<TemperatureAlert>() {
                @Override
                public void onChanged(TemperatureAlert temperatureAlert) {
                    if (temperatureAlert!=null){
                        tempMin.setText(String.valueOf(temperatureAlert.getTemperatureMin()));
                        tempMax.setText(String.valueOf(temperatureAlert.getTemperatureMax()));
                    }
                }
            });
        }

        LiveData<HumidityAlert> humidityAlert = setAlertViewModel.getHumidityAlert(1);
        if (humidityAlert!=null){
            humidityAlert.observe(this, new Observer<HumidityAlert>() {
                @Override
                public void onChanged(HumidityAlert humidityAlert) {
                    if (humidityAlert!=null){
                        humMin.setText(String.valueOf(humidityAlert.getHumidityMin()));
                        humMax.setText(String.valueOf(humidityAlert.getHumidityMax()));
                    }
                }
            });
        }

        LiveData<Co2Alert> co2Alert = setAlertViewModel.getCo2Alert(1);
        if (co2Alert!=null){
            co2Alert.observe(this, new Observer<Co2Alert>() {
                @Override
                public void onChanged(Co2Alert co2Alert) {
                    if (co2Alert!=null){
                        co2Min.setText(String.valueOf(co2Alert.getCo2Min()));
                        co2Max.setText(String.valueOf(co2Alert.getCo2Max()));
                    }
                }
            });
        }



    }


    //test for update statement
    public void testUpdate(){
        Log.e("testupdate", "==============================");
//        TemperatureAlert t= new TemperatureAlert(1, 10, 30);
//        setAlertViewModel.updateTemperatureAlert(t);
//        LiveData<TemperatureAlert> t1 = setAlertViewModel.getTemperatureAlert(1);
//       t1.observe(getActivity(), new Observer<TemperatureAlert>() {
//           @Override
//           public void onChanged(TemperatureAlert temperatureAlert) {
//               Log.e("tal", temperatureAlert.toString());
//           }
//       });
        HumidityAlert h2= new HumidityAlert(1, 90, 130);
        setAlertViewModel.addHumidityAlert(h2);
        HumidityAlert h= new HumidityAlert(1,30,50);
        setAlertViewModel.updateHumidityAlert(h);
        setAlertViewModel.getHumidityAlert(1).observe(this, new Observer<HumidityAlert>() {
            @Override
            public void onChanged(HumidityAlert humidityAlert) {
                Log.e("hum", humidityAlert.toString());
            }
        });
    }

//get current data from the server
    public void initData(){
        Measurements measurements= setAlertViewModel.getMeasurementsMutableLiveData().getValue();
        if (measurements!=null){
            temperature= measurements.getTemperature();
            co2= measurements.getCo2();
            humidity=measurements.getHumidity();

            setAlertViewModel.getMeasurementsMutableLiveData().observe(getActivity(), new Observer<Measurements>() {
                @Override
                public void onChanged(Measurements measurements) {
                    temperature= measurements.getTemperature();
                    co2= measurements.getCo2();
                    humidity=measurements.getHumidity();
                    compareData();
                }
            });
        }



    }

//compare current data and alert data
    public void compareData(){

        LiveData<TemperatureAlert> temperatureAlert = setAlertViewModel.getTemperatureAlert(1);
        if (temperatureAlert!=null){
            temperatureAlert.observe(this, new Observer<TemperatureAlert>() {
                @Override
                public void onChanged(TemperatureAlert temperatureAlert) {
                    if (temperatureAlert!=null){
                        if (temperature<= temperatureAlert.getTemperatureMin()){
                           String  title= "the temperature is too low";
                           String text= "The value of temperature is "+temperature;
                            createNotification(title, text,0);
                        }

                        if (temperature>= temperatureAlert.getTemperatureMax()){
                            String title= "the temperature is too high";
                            String text= "The value of temperature is "+temperature;
                            createNotification(title, text,1);
                        }
                    }
                }
            });
        }

        LiveData<HumidityAlert> humidityAlert = setAlertViewModel.getHumidityAlert(1);
        if (humidityAlert!=null){
            humidityAlert.observe(this, new Observer<HumidityAlert>() {
                @Override
                public void onChanged(HumidityAlert humidityAlert) {
                    if (humidityAlert!=null){
                        if (humidity<= humidityAlert.getHumidityMin()){
                            String  title= "the humidity is too low";
                            String text= "The value of humidity is "+humidity;
                            createNotification(title, text,2);
                        }

                        if (humidity>= humidityAlert.getHumidityMax()){
                            String title= "the humidity is too high";
                            String text= "The value of humidity is "+humidity;
                            createNotification(title, text,3);
                        }
                    }
                }
            });
        }

        LiveData<Co2Alert> co2Alert = setAlertViewModel.getCo2Alert(1);
        if (co2Alert!=null){
            co2Alert.observe(this, new Observer<Co2Alert>() {
                @Override
                public void onChanged(Co2Alert co2Alert) {
                    if (co2Alert!=null){
                        if (co2<= co2Alert.getCo2Min()){
                            String title= "the co2 is too low";
                            String text= "The value of co2 is "+co2;
                            createNotification(title, text,4);
                        }

                        if (co2>= co2Alert.getCo2Max()){
                            String  title= "the co2 is too high";
                            String text= "The value of co2 is "+co2;
                            createNotification(title, text,5);
                        }
                    }



                }
            });
        }








    }



    public void createNotification(String title, String contentText, int id){
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

        notificationManager.notify(id, notification);
    }
}