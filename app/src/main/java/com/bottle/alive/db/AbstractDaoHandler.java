package com.bottle.alive.db;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 数据表操作抽象类，封装了GreenDao的insert，update，insertOrReplace，delete
 */
public abstract class AbstractDaoHandler<T>{

    protected AbstractDao<T, Long> dao;

    public AbstractDaoHandler(AbstractDao<T, Long> dao) {
        this.dao = dao;
    }

    /**
     * @description 插入一条数据记录，新增一条数据记录时调用
     * @param data 实体
     */
    public long insert(T data) {
        return dao.insert(data);
    }

    /**
     * @description 插入多条数据记录
     * @param data 数组
     */
    public void insertInTx(List<T> data) {
        dao.insertInTx(data);
    }

    /**
     * @description 更新一条数据，data必须是有id的
     * @param data 数据表中的记录，或者有唯一值的记录
     */
    public void update(T data) {
        dao.update(data);
    }

    /**
     * @description 更新一组数据
     * @param data
     */
    public void updateInTx(List<T> data) {
        dao.updateInTx(data);
    }

    /**
     * @description 插入或者更新一条数据记录(如果本地存在(有id)这条记录则更新，否则新增)
     * @param data
     */
    public long insertOrReplace(T data) {
        return dao.insertOrReplace(data);
    }

    /**
     * @description 以事务的方式插入或者更新一组数据记录(如果本地存在(有id)这条记录则更新，否则新增)
     * @param data
     */
    public void insertOrReplaceInTx(List<T> data) {
        dao.insertOrReplaceInTx(data);
    }

    /**
     * @description 如果知道数据记录的id，那么可以通过id，也就是关键字key，查找
     * @param id
     */
    public T loadByRowId(Long id) {
        return dao.loadByRowId(id);
    }

    /**
     * @description 查询所有的记录
     */
    public List<T> loadAll() {
        return dao.loadAll();
    }

    /**
     * @description 通过关键字删除一条数据记录，前提是知道关键字
     * @param id
     */
    public void deleteByKey(long id) {
        dao.deleteByKey(id);
    }

    /**
     * @description 删除一个data，data必须是数据表中的一条记录
     * @param data
     */
    public void delete(T data) {
        dao.delete(data);
    }

    /**
     * @description 删除一组记录，data中的对象，必须是数据表中的记录
     * @param data
     */
    public void deleteInTx(List<T> data) {
        dao.deleteInTx(data);
    }

    /**
     * @description 删除表中的所有数据记录
     */
    public void deleteAll() {
        dao.deleteAll();
    }

    /**
     * @description 获取一个 QueryBuilder 对象，用以构造查询条件
     * @return QueryBuilder
     */
    public QueryBuilder<T> queryBuilder() {
        return dao.queryBuilder();
    }

    /**
     * @description 查询符合条件的数据记录
     * @param queryBuilder 查询条件
     * @return 符合条件的数据记录
     */
    public List<T> query(QueryBuilder<T> queryBuilder) {
        List<T> result = null;
        try {
            result = queryBuilder.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * @description 查询数据表中唯一存在的一条记录，提供给unique(T data)调用
     * @param queryBuilder 可以唯一标识这条记录的条件
     * @return 返回唯一记录
     */
    public T unique(QueryBuilder<T> queryBuilder) {
        T result = null;
        try {
            result = queryBuilder.unique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @description 通过构造一个对象（满足唯一性条件），查询唯一的那条记录
     * @param data 构造的一个对象
     * @return 返回数据表中的记录
     */
    public abstract T unique(T data);

}
