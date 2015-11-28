package cn.com.u2be.threadlife.dao.impl;

import cn.com.u2be.threadlife.dao.BaseDao;
import cn.com.u2be.threadlife.util.BeanUtil;
import cn.com.u2be.threadlife.util.PageUtil;
import cn.com.u2be.threadlife.util.PropertyUtil;
import cn.com.u2be.threadlife.util.ReflectUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供hibernate dao的所有操作,<br>
 * 实现类由spring注入HibernateEntityDao和HibernateGenericDao来实现
 */
@Repository
public class BaseDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK> {


    private Class<T> entityClass;


    public BaseDaoImpl() {
        entityClass = (Class<T>) ReflectUtil.getSuperClassGenricType(this.getClass());
    }

    @Resource
    public void setSF(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<T> getAll() {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    public List<T> getAll(String orderBy, boolean isAsc) {
        Assert.hasText(orderBy);
        if (isAsc)
            return (List<T>) getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
        else
            return (List<T>) getHibernateTemplate().findByCriteria(
                    DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
    }

    @Override
    public void removeById(PK id) {
        getHibernateTemplate().delete(get(id));
    }

    @Override
    public Criteria createCriteria(Criterion... criterions) {
        Criteria criteria = currentSession().createCriteria(entityClass);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        return criteria;
    }

    @Override
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
        Criteria criteria = createCriteria();
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        Order order;
        if (isAsc) {
            order = Order.asc(orderBy);
        } else {
            order = Order.desc(orderBy);
        }
        criteria.addOrder(order);
        return criteria;
    }


    @Override
    public List<T> findBy(String propertyName, Object value) {
        Criteria criteria = createCriteria()
                .add(Property.forName(propertyName).eq(value));
        return criteria.list();
    }

    @Override
    public List<T> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
        Criteria criteria = createCriteria()
                .add(Property.forName(propertyName).eq(value));
        Order order;
        if (isAsc) {
            order = Order.asc(orderBy);
        } else {
            order = Order.desc(orderBy);
        }
        criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public T findUniqueBy(String propertyName, Object value) {
        Criteria criteria = createCriteria()
                .add(Property.forName(propertyName).eq(value));
        return (T) criteria.uniqueResult();
    }


    @Override
    public void evit(T entity) {
        getHibernateTemplate().evict(entity);
    }

    @Override
    public T get(PK id) {
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
        Query query = currentSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query;
    }

    @Override
    public List find(String hql, Object... values) {
        return createQuery(hql, values).list();
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public boolean isUnique(T entity, String uniquePropertyNames) {
        Assert.hasText(uniquePropertyNames);
        Criteria criteria = createCriteria().setProjection(Projections.rowCount());
        String[] nameList = uniquePropertyNames.split(",");
        try {
            // 循环加入唯一列
            for (String name : nameList) {
                criteria.add(Restrictions.eq(name, PropertyUtil.getProperty(entity, name)));
            }
            // 以下代码为了如果是update的情况,排除entity自身.
            String idName = getIdName(entityClass);
            // 取得entity的主键值
            Serializable id = getId(entityClass, entity);
            // 如果id!=null,说明对象已存在,该操作为update,加入排除自身的判断
            if (id != null)
                criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
        } catch (Exception e) {
            ReflectionUtils.handleReflectionException(e);
        }
        return (Integer) criteria.uniqueResult() == 0;
    }


    /**
     * 取得对象的主键值,辅助函数.
     */
    @Override
    public Serializable getId(Class entityClass, Object entity) {
        Assert.notNull(entity);
        Assert.notNull(entityClass);
        return (Serializable) PropertyUtil.getProperty(entity, getIdName(entityClass));
    }

    /**
     * 取得对象的主键名,辅助函数.
     */
    @Override
    public String getIdName(Class clazz) {
        Assert.notNull(clazz);
        ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
        Assert.notNull(meta, "Class " + clazz + " not define in hibernate session factory.");
        String idName = meta.getIdentifierPropertyName();
        Assert.hasText(idName, clazz.getSimpleName() + " has no identifier property define.");
        return idName;
    }

    /**
     * 分页查询函数，使用hql.
     *
     * @param pageNo 页号,从1开始.
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageUtil pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        // Count查询
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List countlist = getHibernateTemplate().find(countQueryString, values);
        long totalCount = (Long) countlist.get(0);
        if (totalCount < 1)
            return new PageUtil();
        // 实际查询返回分页对象
        int startIndex = PageUtil.getStartOfPage(pageNo, pageSize);
        Query query = createQuery(hql, values);
        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new PageUtil(startIndex, totalCount, pageSize, list);
    }


    /**
     * @param hql      查询sql
     * @param start    分页从哪一条数据开始
     * @param pageSize 每一个页面的大小
     * @param values   查询条件
     * @return page对象
     * @author Scott.wanglei
     * @since 2008-7-21
     */
    @Override
    public PageUtil dataQuery(String hql, int start, int pageSize, Object... values) {
        // Count查询
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List countlist = getHibernateTemplate().find(countQueryString, values);
        long totalCount = (Long) countlist.get(0);
        if (totalCount < 1)
            return new PageUtil();
        // 实际查询返回分页对象
        int startIndex = start;
        Query query = createQuery(hql, values);
        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new PageUtil(startIndex, totalCount, pageSize, list);
    }

    /**
     * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>.
     *
     * @param pageNo 页号,从1开始.
     * @return 含总记录数和当前页数据的Page对象.
     */
    @Override
    public PageUtil pagedQuery(Criteria criteria, int pageNo, int pageSize) {
        Assert.notNull(criteria);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        CriteriaImpl impl = (CriteriaImpl) criteria;
        // 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
        Projection projection = impl.getProjection();
        List<CriteriaImpl.OrderEntry> orderEntries;
        try {
            orderEntries = (List) BeanUtil.forceGetProperty(impl, "orderEntries");
            BeanUtil.forceSetProperty(impl, "orderEntries", new ArrayList());
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility throw ");
        }
        // 执行查询
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        // 将之前的Projection和OrderBy条件重新设回去
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        try {
            BeanUtil.forceSetProperty(impl, "orderEntries", orderEntries);
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility throw ");
        }
        // 返回分页对象
        if (totalCount < 1)
            return new PageUtil();
        int startIndex = PageUtil.getStartOfPage(pageNo, pageSize);
        ;
        List list = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new PageUtil(startIndex, totalCount, pageSize, list);
    }

    /**
     * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>.
     *
     * @param pageNo 页号,从1开始.
     * @return 含总记录数和当前页数据的Page对象.
     */
    @Override
    public PageUtil pagedQuery(int pageNo, int pageSize, Criterion... criterions) {
        Criteria criteria = createCriteria(criterions);
        return pagedQuery(criteria, pageNo, pageSize);
    }

    /**
     * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>.
     *
     * @param pageNo 页号,从1开始.
     * @return 含总记录数和当前页数据的Page对象.
     */
    @Override
    public PageUtil pagedQuery(int pageNo, int pageSize, String orderBy, boolean isAsc,
                               Criterion... criterions) {
        Criteria criteria = createCriteria(orderBy, isAsc, criterions);
        return pagedQuery(criteria, pageNo, pageSize);
    }


    /**
     * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
     */
    private static String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     */
    private static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order//s*by[//w|//W|//s|//S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }


}