package com.android.news.repo;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.android.news.model.Model;

import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * This class used to insert data into the database
 */
public class Repository {
    public hacksDao hacksDao;
    public LiveData<List<Model>> getAllData;
    private HackDatabase database;

    public Repository(Application application){
        database= HackDatabase.getInstance(application);
        hacksDao =database.hacksDao();
        getAllData= hacksDao.getAllData();

    }

    /**
     * Insert data into the table of Database
     * @param models insert the data
     */
    public void insert(List<Model> models){
        Log.d("REPOSITORY", "insert: ");
        new InsertAsyncTask(hacksDao).execute(models);

    }

    /**
     * Observe the data from the every request
     * @return list of model retrive from table
     */
    public LiveData<List<Model>> getAllData(){
        return getAllData;
    }

    /**
     * This async task used to load the  data ad insert the data into the  table
     */
    private static class InsertAsyncTask extends AsyncTask<List<Model>,Void,Void>{
        private hacksDao hacksDao;

        public InsertAsyncTask(hacksDao catDao)
        {
            this.hacksDao =catDao;
        }
        @Override
        protected Void doInBackground(List<Model>... lists) {
            try {
                hacksDao.insert(lists[0]);
            }catch (ConcurrentModificationException exception){
                Log.d("Repo", "Exception : "+exception);
            }
            return null;
        }
    }

}