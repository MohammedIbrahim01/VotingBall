package com.example.android.crystalball.OptionPackage;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "OptionsTable")
public class OptionEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;

    @Ignore
    public OptionEntry(String description) {
        this.description = description;
    }
    @Ignore
    public OptionEntry(int id) {
        this.id = id;
    }

    public OptionEntry(int id, String description) {
        this.id = id;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
