package com.android.news.model;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.news.repo.Repository;

import java.util.List;

/**
 * This view model class used to handle the data from room.
 */
public class HackerViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<List<Model>> getAllData;

    public HackerViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        getAllData=repository.getAllData();
    }

    public void insert(List<Model> data){
        repository.insert(data);
    }

    public LiveData<List<Model>> getAllData()
    {
        return getAllData;
    }
}
