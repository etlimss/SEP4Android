package com.example.sep4android;

import android.app.Application;
import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.sep4android.data.networking.ClientRepository;
import com.example.sep4android.viewModel.LoginViewModel;
import com.example.sep4android.viewModel.SignUpVM;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    ClientRepository repo;

    @Mock
    Application app;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Test
    public void signinVMTest() {
        SignUpVM vm = new SignUpVM(app, repo);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        vm.getUsername().setValue("test");
        vm.getPassword().setValue("test");

        vm.signUpAccount();

        verify(repo, times(1)).signUpAccount(captor.capture(), captor.capture());

        List<String> args = captor.getAllValues();
        assertEquals("test", args.get(0));
        assertEquals("test", args.get(1));
    }


    @Test
    public void loginVMTest() {
        LoginViewModel vm = new LoginViewModel(app, repo);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        vm.getUsername().setValue("test");
        vm.getPassword().setValue("test");

        vm.loginAccount();

        verify(repo, times(1)).loginAccount(captor.capture(), captor.capture());

        List<String> args = captor.getAllValues();
        assertEquals("test", args.get(0));
        assertEquals("test", args.get(1));
    }
}