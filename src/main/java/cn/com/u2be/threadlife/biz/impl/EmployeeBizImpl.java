package cn.com.u2be.threadlife.biz.impl;

import cn.com.u2be.threadlife.biz.EmployeeBiz;
import cn.com.u2be.threadlife.dao.EmployeeDao;
import cn.com.u2be.threadlife.dao.VersionDao;
import cn.com.u2be.threadlife.entity.Employee;
import cn.com.u2be.threadlife.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ã÷ on 2015/11/26.
 */
@Service
public class EmployeeBizImpl implements EmployeeBiz {
    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private VersionDao versionDao;


    public List<Employee> getEmployeeList() {
        return employeeDao.getEmployeeList();
    }

    public Employee getEmployee(Long id) {
        return employeeDao.getEmployee(id);
    }

    @Override
    public PageUtil getEmployeeByPage(int size, int pageNo) {
        return employeeDao.pagedQuery(pageNo, size);
    }

    @Override
    public long save(Employee employee) {
        versionDao.updataVersion(1);
        return employeeDao.save(employee);
    }

    @Override
    public void update(Employee employee) {
        versionDao.updataVersion(1);
        employeeDao.update(employee);
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setVersionDao(VersionDao versionDao) {
        this.versionDao = versionDao;
    }
}
