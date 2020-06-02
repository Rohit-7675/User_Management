package com.rppatil.inceptivetech.dbhandler.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rppatil.inceptivetech.dbhandler.entity.UserRegistration;

import java.util.List;

@Dao
public interface UserRegistrationDao {
    @Query("SELECT COUNT(*) FROM UserRegistration")
    int getcount();
    @Insert
    void insert(UserRegistration userRegistration);
    @Query("SELECT * FROM UserRegistration")
    List<UserRegistration> getAll();
    @Query("DELETE FROM UserRegistration")
    void deleteAll();
}
