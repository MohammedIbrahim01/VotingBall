package com.example.android.crystalball.OptionPackage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OptionDao {

    @Query("Select * from OptionsTable ")
    List<OptionEntry> loadAllOptions();

    @Insert
    void insertOption (OptionEntry optionEntry);

    @Delete
    void deleteOption (OptionEntry optionEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updataOption (OptionEntry optionEntry);

/*    @Query( "select max(id) from optionstable")
    int getNumberOfOptions();*/


}
