package com.rppatil.inceptivetech.dbhandler.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rppatil.inceptivetech.dbhandler.entity.CityInfo;

import java.util.List;

@Dao
public interface CityInfoDao {
    @Query("SELECT COUNT(*) FROM CityInfo")
    int getcount();
    @Insert
    void insert(CityInfo cityInfo);
    @Query("SELECT * FROM CityInfo")
    List<CityInfo> getAll();
    @Query("DELETE FROM CityInfo")
    void deleteAll();
    @Query("SELECT * FROM CityInfo where state_id = :state_id")
    List<CityInfo> getStateWiseCity(int state_id);
}
