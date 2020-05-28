package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    CacheManager mCacheManager;
    EditText name_text,surname_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCacheManager=new CacheManager();
        name_text=(EditText)findViewById(R.id.edit1);
        surname_text=(EditText)findViewById(R.id.edit2);


    }

    public void addEntry(View view) {

        CachedEntry newCachedObject=new CachedEntry();
        newCachedObject.setUrl(name_text.getText().toString());
        newCachedObject.setFilePath(surname_text.getText().toString());
        name_text.setText("");
        surname_text.setText("");
        mCacheManager.AddUrl(newCachedObject);
        Log.d("hey","entry added");

    }

    public void FindEntry(View view) {

        CachedEntry EntrywithURL=mCacheManager.FindUrl(name_text.getText().toString());
        Log.d("hey",EntrywithURL.getUrl()+" "+EntrywithURL.getFilePath()+" "+EntrywithURL.getResourceID());
    }



    public void print(View view) {
        mCacheManager.print();
    }

    public void deleteTable(View view) {

    }
}