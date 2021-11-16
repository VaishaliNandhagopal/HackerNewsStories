package com.android.news.repo;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.android.news.model.Model;

import java.util.ConcurrentModificationException;
import java.util.List;

public class Repository {
    public hacksDao hacksDao;
    public LiveData<List<Model>> getAllData;
    private HackDatabase database;

    public Repository(Application application){
        database= HackDatabase.getInstance(application);
        hacksDao =database.hacksDao();
        getAllData= hacksDao.getAllData();

    }

    public void insert(List<Model> cats){
        Log.d("REPOSITORY", "insert: ");
        new InsertAsyncTask(hacksDao).execute(cats);

    }

    public LiveData<List<Model>> getAllData(){
        return getAllData;
    }
    private static class InsertAsyncTask extends AsyncTask<List<Model>,Void,Void>{
        private hacksDao hacksDao;

        public InsertAsyncTask(hacksDao catDao)
        {
            this.hacksDao =catDao;
        }
        @Override
        protected Void doInBackground(List<Model>... lists) {
            Log.d("Repo", "doInBackground: "+lists.toString());
            try {
                hacksDao.insert(lists[0]);
            }catch (ConcurrentModificationException exception){
                Log.d("Repo", "Eception : "+exception);
            }
            return null;
        }
    }

}