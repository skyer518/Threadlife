package cn.com.u2be.threadlife.action;

import cn.com.u2be.threadlife.biz.CustomerBiz;
import cn.com.u2be.threadlife.entity.Customer;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by �� on 2015/11/26.
 */
@ParentPackage("struts-default")
@Namespace("/")
@Controller
public class CustomerAction {

    @Resource
    private CustomerBiz customerBiz;

    private Customer customer;

    private List<Customer> customerList;

    @Action(value = "customer", results = {
            @Result(name = "success", location = "/customer.jsp")
    })
    public String customer() {
        customerList = customerBiz.getCustomerList();
        return "success";
    }

    @Action(value = "customer_show", results = {
            @Result(name = "success", location = "/customer_show.jsp")
    })
    public String customer_show() {
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

    public CustomerBiz getCustomerBiz() {
        return this.customerBiz;
    }
}
