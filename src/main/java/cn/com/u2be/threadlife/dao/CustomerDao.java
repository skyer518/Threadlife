package cn.com.u2be.threadlife.dao;

import cn.com.u2be.threadlife.entity.Customer;

import java.util.List;

/**
 * Created by �� on 2015/11/26.
 */
public interface CustomerDao {

    Customer getCustomer(long id);

    List<Customer> getCustomerList();
}
