package com.bottle.alive.db;

import com.bottle.alive.db.dao.DogDaoProxy;
import com.bottle.alive.db.dao.UserDaoProxy;
import com.bottle.alive.gen.DaoSession;

public class DaoManager {

    private static DaoManager instance;

    public static DaoManager getInstance() {
        if(instance == null) {
            synchronized (DaoManager.class) {
                if (instance == null) {
                    instance = new DaoManager();
                }
            }
        }
        return instance;
    }

    private DaoSession mDaoSession;
    private DogDaoProxy mDogDaoHandler;
    private UserDaoProxy mUserDaoHandler;

    private DaoManager() {
        mDaoSession = MyDatabase.getInstance().getDaoSession();
        mUserDaoHandler = new UserDaoProxy(mDaoSession.getUserDao());
        mDogDaoHandler = new DogDaoProxy(mDaoSession.getDogDao());
    }

    public UserDaoProxy getUserDaoHandler() {
        return mUserDaoHandler;
    }

    public DogDaoProxy getDogDaoHandler() {
        return mDogDaoHandler;
    }
}
