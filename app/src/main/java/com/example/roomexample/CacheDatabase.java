package com.example.roomexample;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = CachedEntry.class,version = 1)
public abstract class CacheDatabase extends RoomDatabase {

    public abstract Cache_Dao cache_dao();

    private static volatile CacheDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CacheDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CacheDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CacheDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
