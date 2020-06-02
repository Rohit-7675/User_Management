package com.rppatil.inceptivetech.dbhandler.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rppatil.inceptivetech.dbhandler.entity.StateInfo;

import java.util.List;

@Dao
public interface StateInfoDao {
    @Query("SELECT COUNT(*) FROM StateInfo")
    int getcount();
    @Insert
    void insert(StateInfo stateInfo);
    @Query("SELECT * FROM StateInfo")
    List<StateInfo> getAll();
    @Query("DELETE FROM StateInfo")
    void deleteAll();
}
