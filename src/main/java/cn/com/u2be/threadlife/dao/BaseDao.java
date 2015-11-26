package cn.com.u2be.threadlife.dao;

import java.io.Serializable;
import java.util.List;

/**
 * BaseDAO 定义DAO的通用操作
 *
 * @author Monday
 */
public interface BaseDao<T> {
    /**
     * save entity
     *
     * @param entity
     */
    void save(T entity);

    /**
     * update entity
     *
     * @param entity
     */
    void update(T entity);

    /**
     * delete entity
     *
     * @param id
     */
    void delete(Serializable id);

    /**
     * 用id查对象
     *
     * @param id
     */
    T findById(Serializable id);

    /**
     * 用sql语句查询
     *
     * @param hql
     * @param params
     */
    List<T> findByHQL(String hql, Object... params);

}
