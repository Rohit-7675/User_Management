package com.rppatil.inceptivetech.dbhandler.database_client;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rppatil.inceptivetech.dbhandler.dao.CityInfoDao;
import com.rppatil.inceptivetech.dbhandler.dao.StateInfoDao;
import com.rppatil.inceptivetech.dbhandler.dao.UserRegistrationDao;
import com.rppatil.inceptivetech.dbhandler.entity.CityInfo;
import com.rppatil.inceptivetech.dbhandler.entity.StateInfo;
import com.rppatil.inceptivetech.dbhandler.entity.UserRegistration;

@Database(entities = {CityInfo.class, StateInfo.class, UserRegistration.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CityInfoDao cityInfoDao();
    public abstract StateInfoDao stateInfoDao();
    public abstract UserRegistrationDao userRegistrationDao();
}
