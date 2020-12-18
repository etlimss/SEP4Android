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

    public LiveData<List<Measurements>> getListMutableLiveData() {
        return clientRepository.getListMutableLiveData();
    }

    public void getHistoryFromServer(HistoryRequest body){
        clientRepository.getHistoryFromServer(body);
    }

}