package com.example.sep4android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sep4android.R;
import com.example.sep4android.databinding.FragmentAccountBinding;
import com.example.sep4android.databinding.FragmentCurrentdataBinding;
import com.example.sep4android.viewModel.AccountViewModel;

public class AccountFragment extends Fragment {

    private AccountViewModel accountViewModel;
    FragmentAccountBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        View root= binding.getRoot();
        binding.setAccountViewModel(accountViewModel);
        binding.setLifecycleOwner(this);
        accountViewModel.getRandomUser();

        return root;
    }
}