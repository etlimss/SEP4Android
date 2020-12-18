package com.example.sep4android.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4android.R;
import com.example.sep4android.data.AlertModels.Co2Alert;
import com.example.sep4android.data.AlertModels.HumidityAlert;
import com.example.sep4android.data.AlertModels.TemperatureAlert;
import com.example.sep4android.databinding.FragmentSetAlertBinding;
import com.example.sep4android.viewModel.SetAlertViewModel;


public class SetAlertFragment extends Fragment {
    FragmentSetAlertBinding binding;
    Dialog tempDialog, humDialog, co2Dialog;
    EditText tempMin, tempMax, humMin, humMax, co2Min, co2Max;
    Button btnTemp, btnHum, btnCo2,btnNot;
    SetAlertViewModel setAlertViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_set_alert, container, false);
        View root= binding.getRoot();
        setAlertViewModel= new ViewModelProvider(this).get(SetAlertViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setSetAlertViewModel(setAlertViewModel);
        binding.setSetAlertFragment(this);
        initDialog();
        initListeners(root);
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
        co2Dialog= new Dialog(getContext());
        co2Dialog.setContentView(R.layout.co2_dialog);
        co2Dialog.getWindow().setLayout(width,height);

        co2Min = co2Dialog.findViewById(R.id.co2Min);
        co2Max = co2Dialog.findViewById(R.id.co2Max);
        btnCo2 = co2Dialog.findViewById(R.id.btnInsertCo2);
    }


    public void initListeners(View view)
    {
        ImageView imgViewSetHumidity = view.findViewById(R.id.imgViewSetHum);
        ImageView imgViewSetTemperature = view.findViewById(R.id.imgViewSetTemperature);
        ImageView imgViewSetCo2 = view.findViewById(R.id.imgViewSetCo2);
        imgViewSetTemperature.setOnClickListener(v -> {

           tempDialog.show();

            btnTemp.setOnClickListener(v13 -> {
                tempDialog.dismiss();
                TemperatureAlert tmpAlert = new TemperatureAlert(1,Double.parseDouble(tempMin.getText().toString()),Double.parseDouble(tempMax.getText().toString()));
                // We need to check if we have a temperature alert in the db with userID
                setAlertViewModel.getTemperatureAlert(tmpAlert.getUserId()).observe(getViewLifecycleOwner(), new Observer<TemperatureAlert>() {
                    @Override
                    public void onChanged(TemperatureAlert temperatureAlert) {
                        if(temperatureAlert != null)
                        {
                            setAlertViewModel.updateTemperatureAlert(tmpAlert);
                        }
                        else{
                            setAlertViewModel.addTemperatureAlert(tmpAlert);
                        }
                    }
                });
            });
        });
        imgViewSetHumidity.setOnClickListener(v -> {
                    humDialog.show();
                    btnHum.setOnClickListener(v12 -> {
                        humDialog.dismiss();
                        HumidityAlert humAlert = new HumidityAlert(1, Double.parseDouble(humMin.getText().toString()), Double.parseDouble(humMax.getText().toString()));
                        // We need to check if we have a temperature alert in the db with userID
                        setAlertViewModel.getHumidityAlert(humAlert.getUserId()).observe(getViewLifecycleOwner(), new Observer<HumidityAlert>() {
                            @Override
                            public void onChanged(HumidityAlert humidityAlert) {
                                if (humidityAlert != null) {
                                    setAlertViewModel.updateHumidityAlert(humAlert);
                                } else {
                                    setAlertViewModel.addHumidityAlert(humAlert);
                                }
                            }
                        });
                    });
                });

        imgViewSetCo2.setOnClickListener(v15 -> {
            co2Dialog.show();
            btnCo2.setOnClickListener(v1 -> {
                co2Dialog.dismiss();
                Co2Alert co2Alert = new Co2Alert(1, Double.parseDouble(co2Min.getText().toString()), Double.parseDouble(co2Max.getText().toString()));

                setAlertViewModel.getCo2Alert(co2Alert.getUserId()).observe(getViewLifecycleOwner(), new Observer<Co2Alert>() {
                    @Override
                    public void onChanged(Co2Alert co2Alert1) {
                        if (co2Alert1 != null) {
                            setAlertViewModel.updateCo2Alert(co2Alert);
                        } else {
                            setAlertViewModel.addCo2Alert(co2Alert);
                        }
                    }
                });
            });
        });
    }
}