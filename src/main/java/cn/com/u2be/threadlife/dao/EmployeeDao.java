package cn.com.u2be.threadlife.dao;

import cn.com.u2be.threadlife.entity.Employee;

import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
public interface EmployeeDao extends BaseDao<Employee, Long> {

    Employee getEmployee(long id);

    List<Employee> getEmployeeList();
}
