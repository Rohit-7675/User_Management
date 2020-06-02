package com.rppatil.inceptivetech.dbhandler.Repository;

import android.content.Context;
import android.util.Log;

import com.rppatil.inceptivetech.dbhandler.database_client.DatabaseClient;
import com.rppatil.inceptivetech.dbhandler.entity.StateInfo;

import java.util.List;

public class StateInfoRepository {
    private Context mCtx;
    private static StateInfoRepository mInstance;

    private StateInfoRepository(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized StateInfoRepository getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new StateInfoRepository(mCtx);
        }
        return mInstance;
    }

    public void insert(StateInfo stateInfo){
        //adding to database
        DatabaseClient.getInstance(mCtx).getAppDatabase().stateInfoDao().insert(stateInfo);
        Log.d("alphabet_data","inserted");
    }

    public List<StateInfo> getAll(){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().stateInfoDao().getAll();
    }

    public int  getCount(){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().stateInfoDao().getcount();
    }
    public void deleteAll(){
        DatabaseClient.getInstance(mCtx).getAppDatabase().stateInfoDao().deleteAll();
    }
}
