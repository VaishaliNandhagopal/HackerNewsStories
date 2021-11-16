package com.android.news.repo;


import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.news.model.Model;

@Database(entities = {Model.class},version =8 )
public abstract class HackDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "HacksNews";
    public abstract hacksDao hacksDao();
    public static volatile HackDatabase INSTANCE = null;

    public static HackDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (HackDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, HackDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyn(INSTANCE);
        }
    };

    static  class  PopulateDbAsyn extends AsyncTask<Void,Void,Void>{
        private hacksDao hacksDao;
        public PopulateDbAsyn(HackDatabase hackDatabase)
        {
            hacksDao = hackDatabase.hacksDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            hacksDao.deleteAll();
            return null;
        }
    }
}