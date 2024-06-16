package com.bhapkar.dairyfarm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bhapkar.dairyfarm.data.model.Cow;

import java.util.List;

@Dao
public interface CowDao {

    @Insert
    void insert(Cow cow);

    @Update
    void update(Cow cow);

    @Delete
    void delete(Cow cow);

    @Query("SELECT * FROM cow_table WHERE id = :id")
    LiveData<Cow> getCowById(int id);

    @Query("SELECT * FROM cow_table")
    LiveData<List<Cow>> getAllCows();
}
