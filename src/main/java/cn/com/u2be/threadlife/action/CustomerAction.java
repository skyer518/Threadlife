package cn.com.u2be.threadlife.action;

import cn.com.u2be.threadlife.biz.CustomerBiz;
import cn.com.u2be.threadlife.entity.Customer;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
public class CustomerAction {

    private CustomerBiz customerBiz;

    private Customer customer;

    private List<Customer> customerList;

    @Action(value = "customer", results = {
            @Result(name = "success", location = "/customer.jsp")
    })
    String customer() {
        customerList = customerBiz.getCustomerList();
        return "success";
    }

    @Action(value = "customer", results = {
            @Result(name = "success", location = "/customer_show.jsp")
    })
    String customer_show() {
        customer = customerBiz.getCustomer(customer.getId());
        return "success";
    }


    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomerList() {
        return this.customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
