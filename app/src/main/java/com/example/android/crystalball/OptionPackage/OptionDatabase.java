package com.example.android.crystalball.OptionPackage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {OptionEntry.class},version = 1,exportSchema = false)
public abstract class OptionDatabase extends RoomDatabase {
    private static final String LOG_TAG = OptionDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "OptionsListDatabase";
    private static OptionDatabase sInstance;


    public static OptionDatabase getInstance(Context context) {
        if (sInstance == null) {
            Log.v(LOG_TAG, "create the Option database");
            sInstance = Room.databaseBuilder(context.getApplicationContext(), OptionDatabase.class,
                    OptionDatabase.DATABASE_NAME).allowMainThreadQueries().build();
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;

    }
   public abstract OptionDao OptionDao();
}
