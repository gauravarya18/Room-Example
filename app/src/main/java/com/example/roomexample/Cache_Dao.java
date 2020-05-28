package com.example.roomexample;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Cache_Dao {

    @Insert
    public void addEntry(CachedEntry newCachedObject);

    @Query("Select * from  Cache_Table")
    public List<CachedEntry> getAllEntries();

    @Delete
    public void deleteEntry(CachedEntry mCachedEntry);

}
