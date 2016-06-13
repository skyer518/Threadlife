package cn.com.u2be.threadlife.action;

import cn.com.u2be.threadlife.biz.EmployeeBiz;
import cn.com.u2be.threadlife.entity.Employee;
import cn.com.u2be.threadlife.util.PageUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
@ParentPackage("struts-default")
@Namespace("/")
@Controller
public class EmployeeAction {

    @Resource
    private EmployeeBiz employeeBiz;

    private PageUtil page;

    private int pageNo = 1;
    private int size = 1;


    private Employee employee;

    private List<Employee> employeeList;


    @Action(value = "employee", results = {
            @Result(name = "success", location = "/employee.jsp")
    })
    public String employee() {

        employeeList = employeeBiz.getEmployeeList();
        return "success";
    }

    @Action(value = "employee_page", results = {
            @Result(name = "success", location = "/employee_page.jsp")
    })
    public String employee_page() {

        page = employeeBiz.getEmployeeByPage(size, pageNo);
        employeeList = (List<Employee>) page.getResult();
        return "success";
    }

    @Action(value = "employee_show", results = {
            @Result(name = "success", location = "/employee_show.jsp")
    })
    public String employee_show() {
        employee = employeeBiz.getEmployee(employee.getId());
        return "success";
    }

    @Action(value = "employee_add", results = {
            @Result(name = "success", type = "chain", location = "employee"),
            @Result(name = "error", location = "employee_add.jsp")
    })
    public String employee_add() {
        long save = employeeBiz.save(employee);
        if (save > 0)
            return "success";
        else
            return "error";

    }


    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployeeList() {
        return this.employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public EmployeeBiz getEmployeeBiz() {
        return this.employeeBiz;
    }

    public PageUtil getPage() {
        return this.page;
    }

    public void setPage(PageUtil page) {
        this.page = page;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
