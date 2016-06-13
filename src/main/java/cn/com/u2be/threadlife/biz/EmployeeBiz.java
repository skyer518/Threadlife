package cn.com.u2be.threadlife.biz;

import cn.com.u2be.threadlife.entity.Employee;
import cn.com.u2be.threadlife.util.PageUtil;

import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
public interface EmployeeBiz {

    List<Employee> getEmployeeList();

    Employee getEmployee(Long id);

    PageUtil getEmployeeByPage(int size, int pageNo);

    long save(Employee employee);

    void update(Employee employee);
}
