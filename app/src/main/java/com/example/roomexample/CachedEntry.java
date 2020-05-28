package com.example.roomexample;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cache_Table")
public class CachedEntry {

    @PrimaryKey@NonNull
    private String url="";

    private String filePath;
    private String locationType;
    private String resourceID;
    private int isReadOnly;

    public String getUrl() {
        return url;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getResourceID() {
        return resourceID;
    }

    public int getIsReadOnly() {
        return isReadOnly;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public void setIsReadOnly(int isReadOnly) {
        this.isReadOnly = isReadOnly;
    }
}
