package com.bottle.alive.db.dao;

import com.bottle.alive.db.AbstractDaoHandler;
import com.bottle.alive.db.schema.Dog;

import org.greenrobot.greendao.AbstractDao;

public class DogDaoProxy extends AbstractDaoHandler<Dog> {

    public DogDaoProxy(AbstractDao<Dog, Long> dao) {
        super(dao);
    }

    @Override
    public Dog unique(Dog data) {
        return null;
    }

}
