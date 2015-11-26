package cn.com.u2be.threadlife.biz;

import cn.com.u2be.threadlife.entity.Customer;

import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
public interface CustomerBiz {

    List<Customer> getCustomerList();

    Customer getCustomer(Long id);
}
