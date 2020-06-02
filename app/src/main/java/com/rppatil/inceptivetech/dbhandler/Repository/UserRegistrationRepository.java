package com.rppatil.inceptivetech.dbhandler.Repository;

import android.content.Context;
import android.util.Log;

import com.rppatil.inceptivetech.dbhandler.database_client.DatabaseClient;
import com.rppatil.inceptivetech.dbhandler.entity.UserRegistration;

import java.util.List;

public class UserRegistrationRepository {
    private Context mCtx;
    private static UserRegistrationRepository mInstance;

    private UserRegistrationRepository(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized UserRegistrationRepository getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new UserRegistrationRepository(mCtx);
        }
        return mInstance;
    }

    public void insert(UserRegistration userRegistration){
        //adding to database
        DatabaseClient.getInstance(mCtx).getAppDatabase().userRegistrationDao().insert(userRegistration);
        Log.d("alphabet_data","inserted");
    }

    public List<UserRegistration> getAll(){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().userRegistrationDao().getAll();
    }

    public int  getCount(){
        return DatabaseClient.getInstance(mCtx).getAppDatabase().userRegistrationDao().getcount();
    }
    public void deleteAll(){
        DatabaseClient.getInstance(mCtx).getAppDatabase().userRegistrationDao().deleteAll();
    }

}
