package com.bottle.alive.db.dao;

import com.bottle.alive.db.AbstractDaoHandler;
import com.bottle.alive.db.schema.User;

import org.greenrobot.greendao.AbstractDao;

public class UserDaoProxy extends AbstractDaoHandler<User> {

    public UserDaoProxy(AbstractDao<User, Long> dao) {
        super(dao);
    }

    @Override
    public User unique(User data) {
        return null;
    }
}
