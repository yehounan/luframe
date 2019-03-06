package com.yezi.luframe.service.impl;

import com.yezi.luframe.base.PageInfoData;
import com.yezi.luframe.dao.UserDao;
import com.yezi.luframe.entity.User;
import com.yezi.luframe.param.UserSearchParam;
import com.yezi.luframe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yezi
 * @date 2019/3/6 10:59
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    /**
     * 用户列表查询（分页）
     *
     * @param searchParam
     * @return
     */
    @Override
    public PageInfoData<User> listUser(UserSearchParam searchParam) {
        Pageable pageable = PageRequest.of(searchParam.getPageNum() - 1, searchParam.getPageSize(), Sort.Direction.DESC, "id");
        Specification<User> specification = new Specification() {
            @Nullable
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(searchParam.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + searchParam.getName() + "%");
                    predicates.add(predicate);
                }
                if (!StringUtils.isEmpty(searchParam.getAddress())) {
                    Predicate predicate = criteriaBuilder.like(root.get("address"), "%" + searchParam.getName() + "%");
                    predicates.add(predicate);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<User> page = userDao.findAll(specification, pageable);
        return new PageInfoData<>(page);
    }
}
