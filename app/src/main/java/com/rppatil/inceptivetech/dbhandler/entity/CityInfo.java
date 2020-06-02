package com.rppatil.inceptivetech.dbhandler.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(primaryKeys = {"state_id", "city_id"})
public class CityInfo implements Serializable {
    @ColumnInfo @NotNull
    private int state_id;
    @ColumnInfo @NotNull
    private int city_id;
    @ColumnInfo
    private String city_name;

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
