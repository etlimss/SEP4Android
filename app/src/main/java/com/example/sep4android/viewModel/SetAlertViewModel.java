package com.example.sep4android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sep4android.data.model.AlertValue;
import com.example.sep4android.data.repository.AlertRepository;
import com.example.sep4android.databinding.FragmentSetAlertBindingImpl;

public class SetAlertViewModel extends AndroidViewModel {



    private AlertRepository alertRepository;


    public SetAlertViewModel(@NonNull Application application) {
        super(application);
        alertRepository= AlertRepository.getInstance(application);

    }

    public void insertAlert(AlertValue... alertValues){
        alertRepository.insert(alertValues);
    }

    public void updateAlert(AlertValue... alertValues){
        alertRepository.update(alertValues);
    }

    public LiveData<AlertValue> getAlert(long userId){
        return alertRepository.getAlertValueLiveData(userId);
    }
}
