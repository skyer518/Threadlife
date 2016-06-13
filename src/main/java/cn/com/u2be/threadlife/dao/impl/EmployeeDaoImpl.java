package cn.com.u2be.threadlife.dao.impl;

import cn.com.u2be.threadlife.dao.EmployeeDao;
import cn.com.u2be.threadlife.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<Employee, Long> implements EmployeeDao {


    public Employee getEmployee(long id) {
        return get(id);
    }

    public List<Employee> getEmployeeList() {
        return getAll();
    }
}
