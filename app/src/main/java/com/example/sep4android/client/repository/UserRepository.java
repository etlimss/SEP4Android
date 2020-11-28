package com.example.sep4android.client.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.sep4android.client.model.User;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    private static UserRepository instance;

    private UserRepository(Application application){
        UserDatabase userDatabase= UserDatabase.getInstance(application);
        userDao= userDatabase.userDao();
        allUsers= userDao.getAllUsers();
    }

    public static synchronized UserRepository getInstance(Application application){
        if (instance==null){
            instance= new UserRepository(application);
        }
        return instance;
    }

    void insert(User... users){
        new InsertAsync(userDao).execute(users);
    }

    void update(User... users){
        new UpdateAsync(userDao).execute(users);
    }


    void delete(User... users){
        new DeleteAsync(userDao).execute(users);
    }


    void deleteAllUsers(){
        new DeleteAllUsersAsync(userDao).execute();
    }

    LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    private class InsertAsync extends AsyncTask<User, Void, Void>{
        private UserDao userDao;

        public InsertAsync(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users);
            return null;
        }
    }

    private class UpdateAsync extends AsyncTask<User, Void, Void>{
        private UserDao userDao;

        public UpdateAsync(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users);
            return null;
        }
    }

    private class DeleteAsync extends AsyncTask<User, Void, Void>{
        private UserDao userDao;

        public DeleteAsync(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users);
            return null;
        }
    }

    private class DeleteAllUsersAsync extends AsyncTask<Void, Void, Void>{
        private UserDao userDao;

        public DeleteAllUsersAsync(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }


}
