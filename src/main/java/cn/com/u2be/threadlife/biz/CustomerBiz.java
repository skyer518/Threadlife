package cn.com.u2be.threadlife.biz;

import cn.com.u2be.threadlife.entity.Customer;
import cn.com.u2be.threadlife.util.PageUtil;

import java.util.List;

/**
 * Created by �� on 2015/11/26.
 */
public interface CustomerBiz {

    List<Customer> getCustomerList();

    Customer getCustomer(Long id);

    PageUtil getCustomerByPage(int size, int pageNo);
}
