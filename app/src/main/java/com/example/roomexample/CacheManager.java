package com.example.roomexample;

import android.renderscript.ScriptGroup;
import android.util.Log;

import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import androidx.room.Room;

public class CacheManager {

    private Cache_Dao mCache_Dao;


    CacheManager()
    {
        CacheDatabase db=CacheDatabase.getDatabase(MyApplication.getContext());
        mCache_Dao=db.cache_dao();

    }

    private CachedEntry findEntry(String mUrl)
    {


        Callable<List<CachedEntry>> callable=new Callable<List<CachedEntry>>() {
            @Override
            public List<CachedEntry> call() throws Exception {
                List<CachedEntry> mList=mCache_Dao.getAllEntries();
                return mList;
            }
        };

        Future<List<CachedEntry>> List1=CacheDatabase.databaseWriteExecutor.submit(callable);
        List<CachedEntry> mList;
        try{
           mList = List1.get();
            for(int i=mList.size()-1;i>=0;i--)
            {
                if(mList.get(i).getUrl().matches(mUrl))
                {
                    final CachedEntry found=mList.get(i);
                    return found;
                }
            }
        }
        catch (ExecutionException e){
        }
        catch (InterruptedException i){

        }






        CachedEntry nullEntry=new CachedEntry();
        return nullEntry;

    }

    public void AddUrl(final CachedEntry newEntry)
    {
        final CachedEntry mCachedEntry=findEntry(newEntry.getUrl());
        CacheDatabase.databaseWriteExecutor.execute(()->{
            mCache_Dao.deleteEntry(mCachedEntry);
        });
        CacheDatabase.databaseWriteExecutor.execute(()->{
            mCache_Dao.addEntry(newEntry);
        });

    }

    public CachedEntry FindUrl(String Url)
    {
        CachedEntry mCachedEntry=findEntry(Url);
        CacheDatabase.databaseWriteExecutor.execute(()->{
            mCache_Dao.deleteEntry(mCachedEntry);
        });
        CacheDatabase.databaseWriteExecutor.execute(()->{
            mCache_Dao.addEntry(mCachedEntry);
        });

        return mCachedEntry;
    }

    public void print()
    {

        Callable<List<CachedEntry>> callable=new Callable<List<CachedEntry>>() {
            @Override
            public List<CachedEntry> call() throws Exception {
                List<CachedEntry> mList=mCache_Dao.getAllEntries();
                return mList;
            }
        };

        Future<List<CachedEntry>> List1=CacheDatabase.databaseWriteExecutor.submit(callable);
        List<CachedEntry> mList;
        try{
            mList = List1.get();
            for(int i=mList.size()-1;i>=0;i--)
            {
                Log.d("hey",mList.get(i).getUrl());
            }
        }
        catch (ExecutionException e){
        }
        catch (InterruptedException i){

        }
    }

}
