package com.example.sep4android.client.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurrentDataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CurrentDataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is current data fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}