package com.yezi.luframe.elasticsearch.dao;

import com.yezi.luframe.elasticsearch.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yezi
 * @date 2019/3/12 10:57
 */
@Repository
public interface EmployeeDao extends ElasticsearchRepository<Employee, String> {

}
