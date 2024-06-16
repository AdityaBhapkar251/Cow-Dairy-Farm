package com.bhapkar.dairyfarm;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bhapkar.dairyfarm.data.model.Cow;

@Database(entities = {Cow.class}, version = 1)
public abstract class CowDatabase extends RoomDatabase {

    private static CowDatabase instance;

    public abstract CowDao cowDao();

    public static synchronized CowDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CowDatabase.class, "cow_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
