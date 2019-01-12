package com.bottle.alive.db.schema;

import com.bottle.alive.db.converter.RoleConverter;
import com.bottle.alive.db.typedef.Role;
import com.bottle.alive.gen.DaoSession;
import com.bottle.alive.gen.DogDao;
import com.bottle.alive.gen.UserDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity
public class User {

    @Id
    private Long userId;
    private String userName;
    // private Long createTime;
    private int age;
    private int height;
    @ToMany(referencedJoinProperty = "masterId")
    private List<Dog> pets;
    @Convert(converter = RoleConverter.class, columnType = Integer.class)
    private Role role;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;

    public User(Long userId, String userName, int age, int height) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.height = height;
        this.role = Role.ADMIN;
        // this.createTime = userId;
    }

    @Generated(hash = 2000849538)
    public User(Long userId, String userName, int age, int height, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.height = height;
        this.role = role;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 523013437)
    public List<Dog> getPets() {
        if (pets == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DogDao targetDao = daoSession.getDogDao();
            List<Dog> petsNew = targetDao._queryUser_Pets(userId);
            synchronized (this) {
                if (pets == null) {
                    pets = petsNew;
                }
            }
        }
        return pets;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1767632067)
    public synchronized void resetPets() {
        pets = null;
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
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}
