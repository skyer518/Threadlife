package cn.com.u2be.threadlife.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;


/**
 * 提供hibernate dao的所有操作,<br>
 * 实现类由spring注入HibernateEntityDao和HibernateGenericDao来实现
 */
public interface BaseDao<T, PK extends Serializable> {

    /**
     * 获取全部对象
     */
    public List<T> getAll(Class<T> entityClass);

    /**
     * 获取全部对象,带排序参数.
     */
    public List<T> getAll(Class<T> entityClass,String orderBy, boolean isAsc);

    /**
     * 根据ID移除对象.
     */
    public void removeById(Class<T> entityClass,PK id);

    /**
     * 取得Entity的Criteria.
     */
    public Criteria createCriteria(Criterion... criterions);

    /**
     * 取得Entity的Criteria,带排序参数.
     */
    public Criteria createCriteria(String orderBy, boolean isAsc,
                                   Criterion... criterions);

    /**
     * 根据属性名和属性值查询对象.
     *
     * @return 符合条件的对象列表
     */
    public List<T> findBy(String propertyName, Object value);

    /**
     * 根据属性名和属性值查询对象,带排序参数.
     *
     * @return 符合条件的对象列表
     */
    public List<T> findBy(String propertyName, Object value, String orderBy,
                          boolean isAsc);

    /**
     * 根据属性名和属性值查询单个对象.
     *
     * @return 符合条件的唯一对象 or null
     */
    public T findUniqueBy(String propertyName, Object value);

    /**
     * 判断对象某些属性的值在数据库中唯一.
     *
     * @param uniquePropertyNames 在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
     */
    public boolean isUnique(T entity, String uniquePropertyNames);

    /**
     * 消除与 Hibernate Session 的关联
     */
    public void evit(T entity);


    /**
     * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
     */
    public T get(Class<T> entityClass,PK id);

    /**
     * 保存对象.
     */
    public void save(T o);

    /**
     * 删除对象.
     */
    public void remove(T o);

    public void flush();

    public void clear();

    /**
     * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
     * 留意可以连续设置,如下：
     * <pre>
     * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
     * </pre>
     * 调用方式如下：
     * <pre>
     *        dao.createQuery(hql)
     *        dao.createQuery(hql,arg0);
     *        dao.createQuery(hql,arg0,arg1);
     *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
     * </pre>
     *
     * @param values 可变参数.
     */
    public Query createQuery(String hql, Object... values);


    /**
     * 根据hql查询,直接使用HibernateTemplate的find函数.
     */
    @SuppressWarnings("unchecked")
    public List find(String hql, Object... values);


    /**
     * 在不同的session中关联修改过的托管对象
     */
    public void update(T entity);
}