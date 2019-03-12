package com.yezi.luframe.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * elasticsearch实体类demo
 *
 * @author yezi
 * @date 2019/3/12 10:47
 */
@Data
@Document(indexName = "company", type = "employee", shards = 1, replicas = 0, refreshInterval = "-1")
public class Employee {

    @Id
    private String id;


    private String firstName;


    private String lastName;


    private Integer age = 0;


    private String about;

}
