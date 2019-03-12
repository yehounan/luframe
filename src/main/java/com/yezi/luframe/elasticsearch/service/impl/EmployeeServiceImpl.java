package com.yezi.luframe.elasticsearch.service.impl;

import com.yezi.luframe.elasticsearch.Employee;
import com.yezi.luframe.elasticsearch.dao.EmployeeDao;
import com.yezi.luframe.elasticsearch.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yezi
 * @date 2019/3/12 10:52
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }
}
