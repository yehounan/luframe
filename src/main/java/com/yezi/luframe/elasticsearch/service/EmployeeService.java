package com.yezi.luframe.elasticsearch.service;

import com.yezi.luframe.elasticsearch.Employee;

/**
 * @author yezi
 * @date 2019/3/12 10:50
 */
public interface EmployeeService {

    /**
     * 新增elasticsearch实例对象
     *
     * @param employee
     */
    Employee save(Employee employee);


}
