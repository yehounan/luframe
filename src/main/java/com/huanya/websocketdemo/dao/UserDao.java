package com.huanya.websocketdemo.dao;

import com.huanya.websocketdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @author yezi
 * @date 2019/3/6 10:11
 */
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, Serializable {

}
