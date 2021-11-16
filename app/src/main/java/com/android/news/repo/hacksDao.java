package com.android.news.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.news.model.Model;

import java.util.List;

/**
 * This DAO class for the data retrival and insertion
 */
@Dao
public interface hacksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Model> cats);

    @Query("SELECT DISTINCT * FROM model")
    LiveData<List<Model>>  getAllData();

    @Query("DELETE FROM model")
    void deleteAll();
}
