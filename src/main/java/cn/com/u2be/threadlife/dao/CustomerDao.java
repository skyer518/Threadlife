package cn.com.u2be.threadlife.dao;

import cn.com.u2be.threadlife.entity.Customer;

import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
public interface CustomerDao extends BaseDao<Customer, Long> {

    Customer getCustomer(long id);

    List<Customer> getCustomerList();
}
