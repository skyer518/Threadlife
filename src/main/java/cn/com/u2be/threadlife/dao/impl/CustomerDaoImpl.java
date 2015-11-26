package cn.com.u2be.threadlife.dao.impl;

import cn.com.u2be.threadlife.dao.CustomerDao;
import cn.com.u2be.threadlife.entity.Customer;

import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {


    public Customer getCustomer(long id) {
        return findById(id);
    }

    public List<Customer> getCustomerList() {
        String hql = "FROM customer ";
        return findByHQL(hql);
    }
}
