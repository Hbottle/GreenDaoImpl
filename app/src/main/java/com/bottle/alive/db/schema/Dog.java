package com.bottle.alive.db.schema;

import com.bottle.alive.gen.DaoSession;
import com.bottle.alive.gen.DogDao;
import com.bottle.alive.gen.UserDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

@Entity
public class Dog {

    @Id
    private Long id;
    private Long masterId; // 外码 User
    @ToOne(joinProperty = "masterId")
    private User customer;
    private String name;
    private int age;
    private int weight;
    private String nickName;
    // private boolean eating;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1808256708)
    private transient DogDao myDao;
    @Generated(hash = 2030231897)
    public Dog(Long id, Long masterId, String name, int age, int weight,
            String nickName) {
        this.id = id;
        this.masterId = masterId;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.nickName = nickName;
    }
    @Generated(hash = 2001499333)
    public Dog() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMasterId() {
        return this.masterId;
    }
    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getWeight() {
        return this.weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    @Generated(hash = 8592637)
    private transient Long customer__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 32915164)
    public User getCustomer() {
        Long __key = this.masterId;
        if (customer__resolvedKey == null || !customer__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User customerNew = targetDao.load(__key);
            synchronized (this) {
                customer = customerNew;
                customer__resolvedKey = __key;
            }
        }
        return customer;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1886994007)
    public void setCustomer(User customer) {
        synchronized (this) {
            this.customer = customer;
            masterId = customer == null ? null : customer.getUserId();
            customer__resolvedKey = masterId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 375991494)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDogDao() : null;
    }

}
