package cn.com.u2be.threadlife.dao.impl;

import cn.com.u2be.threadlife.dao.CustomerDao;
import cn.com.u2be.threadlife.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer, Long> implements CustomerDao {


    public Customer getCustomer(long id) {
        return get(id);
    }

    public List<Customer> getCustomerList() {
        return getAll();
    }
}
