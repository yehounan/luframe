package com.yezi.luframe.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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

    @Field(type = FieldType.Text, searchAnalyzer = "ik", analyzer = "ik")
    private String firstName;

    @Field
    private String lastName;

    @Field(type = FieldType.Integer)
    private Integer age = 0;

    @Field
    private String about;

}
