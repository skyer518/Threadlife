package cn.com.u2be.threadlife.dao.impl;

import cn.com.u2be.threadlife.dao.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 提供hibernate dao的所有操作,<br>
 * 实现类由spring注入HibernateEntityDao和HibernateGenericDao来实现
 */
@Repository
public class BaseDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK> {


    @Resource
    public void setSF(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<T> getAll(Class<T> entityClass) {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    public List<T> getAll(Class<T> entityClass, String orderBy, boolean isAsc) {
        Assert.hasText(orderBy);
        if (isAsc)
            return (List<T>) getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
        else
            return (List<T>) getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
    }

    @Override
    public void removeById(Class<T> entityClass, PK id) {
        getHibernateTemplate().delete(get(entityClass, id));
    }

    @Override
    public Criteria createCriteria(Criterion... criterions) {
        return null;
    }

    @Override
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
        return null;
    }

    @Override
    public List<T> findBy(String propertyName, Object value) {
        return null;
    }

    @Override
    public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
        return null;
    }

    @Override
    public T findUniqueBy(String propertyName, Object value) {
        return null;
    }

    @Override
    public boolean isUnique(T entity, String uniquePropertyNames) {
        return false;
    }

    @Override
    public void evit(T entity) {
        getHibernateTemplate().evict(entity);
    }

    @Override
    public T get(Class<T> entityClass, PK id) {
        return getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public void save(T o) {
        getHibernateTemplate().save(o);
    }

    @Override
    public void remove(T o) {
        getHibernateTemplate().delete(o);
    }

    @Override
    public void flush() {
        getHibernateTemplate().flush();
    }

    @Override
    public void clear() {
        getHibernateTemplate().clear();
    }

    @Override
    public Query createQuery(String hql, Object... values) {
        return null;
    }

    @Override
    public List find(String hql, Object... values) {
        return null;
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }


}