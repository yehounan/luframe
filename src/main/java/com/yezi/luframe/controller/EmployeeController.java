package com.yezi.luframe.controller;

import com.yezi.luframe.elasticsearch.Employee;
import com.yezi.luframe.elasticsearch.service.EmployeeService;
import com.yezi.luframe.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yezi
 * @date 2019/3/12 14:06
 */
@RestController
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;


    @PostMapping(value = "/es/employee/save")
    public JsonResult saveEmployee(@Valid @RequestBody Employee employee) {
        return new JsonResult().OK(employeeService.save(employee));
    }

    @GetMapping(value = "/es/employee/delete/{id}")
    public JsonResult deleteEmployee(@PathVariable("id") String id) {
        employeeService.delete(id);
        return new JsonResult().OK();
    }

    @GetMapping(value = "/es/employee/{id}")
    public JsonResult findById(@PathVariable("id") String id) {
        return new JsonResult().OK(employeeService.findById(id));
    }

    @GetMapping(value = "/es/employee/all")
    public JsonResult listAll() {
        return new JsonResult().OK(employeeService.findAll());
    }


}
