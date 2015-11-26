package cn.com.u2be.threadlife.biz.impl;

import cn.com.u2be.threadlife.biz.CustomerBiz;
import cn.com.u2be.threadlife.dao.CustomerDao;
import cn.com.u2be.threadlife.entity.Customer;

import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
public class CustomerBizImpl implements CustomerBiz {

    private CustomerDao customerDao;

    public List<Customer> getCustomerList() {
        return customerDao.getCustomerList();
    }

    public Customer getCustomer(Long id) {
        return customerDao.getCustomer(id);
    }
}
