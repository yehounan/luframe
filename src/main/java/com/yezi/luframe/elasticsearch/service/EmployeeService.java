package com.yezi.luframe.elasticsearch.service;

import com.yezi.luframe.elasticsearch.Employee;

import java.util.List;

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

    /**
     * 删除elasticsearch实例对象
     *
     * @param id
     */
    void delete(String id);


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Employee findById(String id);

    /**
     * 查询所有
     *
     * @return
     */
    List<Employee> findAll();


}
