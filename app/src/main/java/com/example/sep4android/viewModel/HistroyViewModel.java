package com.example.sep4android.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.data.model.HistoryRequest;
import com.example.sep4android.data.model.Measurements;
import com.example.sep4android.data.networking.ClientRepository;

import java.util.List;

public class HistroyViewModel extends ViewModel {

    private ClientRepository clientRepository;


    public HistroyViewModel() {
        clientRepository= ClientRepository.getInstance();

    }

    public MutableLiveData<List<Measurements>> getListMutableLiveData() {
        return clientRepository.getListMutableLiveData();
    }

    public void getHistoryFromServer(String location, String from, String to){

            clientRepository.getHistoryFromServer(location, from, to);
    }

    public List<Measurements> getMeasurementsList() {
        return clientRepository.getMeasurementsList();
    }

}