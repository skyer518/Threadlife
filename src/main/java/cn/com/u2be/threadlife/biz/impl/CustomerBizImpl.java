package cn.com.u2be.threadlife.biz.impl;

import cn.com.u2be.threadlife.biz.CustomerBiz;
import cn.com.u2be.threadlife.dao.CustomerDao;
import cn.com.u2be.threadlife.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
@Service
public class CustomerBizImpl implements CustomerBiz {
    @Resource
    private CustomerDao customerDao;

    public List<Customer> getCustomerList() {
        return customerDao.getCustomerList();
    }

    public Customer getCustomer(Long id) {
        return customerDao.getCustomer(id);
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
