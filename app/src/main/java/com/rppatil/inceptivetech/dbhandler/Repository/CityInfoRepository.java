package com.rppatil.inceptivetech.dbhandler.Repository;

import android.content.Context;
import android.util.Log;

import com.rppatil.inceptivetech.dbhandler.database_client.DatabaseClient;
import com.rppatil.inceptivetech.dbhandler.entity.CityInfo;

import java.util.List;

public class CityInfoRepository {
    private Context mCtx;
    private static CityInfoRepository mInstance;

    private CityInfoRepository(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized CityInfoRepository getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new CityInfoRepository(mCtx);
        }
        return mInstance;
    }

    public void insert(CityInfo cityInfo){
        //adding to database
        DatabaseClient.getInstance(mCtx).getAppDatabase().cityInfoDao().insert(cityInfo);
        Log.d("alphabet_data","inserted");
    }

    public List<CityInfo> getAll(){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().cityInfoDao().getAll();
    }

    public int  getCount(){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().cityInfoDao().getcount();
    }
    public void deleteAll(){
        DatabaseClient.getInstance(mCtx).getAppDatabase().cityInfoDao().deleteAll();
    }

    public List<CityInfo> getStateWiseCity(int state_id){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().cityInfoDao().getStateWiseCity(state_id);
    }
}
