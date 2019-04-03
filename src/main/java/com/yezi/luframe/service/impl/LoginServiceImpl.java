//package com.yezi.luframe.service.impl;
//
//import com.yezi.luframe.constant.Constants;
//import com.yezi.luframe.entity.User;
//import com.yezi.luframe.enums.CodeEnum;
//import com.yezi.luframe.param.LoginParam;
//import com.yezi.luframe.service.LoginService;
//import com.yezi.luframe.service.UserService;
//import com.yezi.luframe.util.JwtUtils;
//import com.yezi.luframe.vo.JsonResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author yezi
// * @date 2019/3/6 15:21
// */
//@Service
//public class LoginServiceImpl implements LoginService {
//
//    @Autowired
//    UserService userService;
//
//    /**
//     * 登录
//     *
//     * @param loginParam
//     * @return
//     */
//    @Override
//    public JsonResult login(LoginParam loginParam, HttpServletRequest request) {
//        User user = userService.findByUsernameAndPassword(loginParam);
//        if (null == user) {
//            return new JsonResult(CodeEnum.USER_NAME_OR_PASSWORD_ERROR.getCode(), CodeEnum.USER_NAME_OR_PASSWORD_ERROR.getMessage(), null);
//        }
//        Map<String, String> map = new HashMap(1);
//        map.put("token", JwtUtils.sign(user, Constants.TOKEN_EXPIRED));
//        //加入到属性供操作日志切面使用
//        request.setAttribute(Constants.ADMIN_USER_ID, user.getId());
//        request.setAttribute(Constants.ADMIN_USERNAME, user.getName());
//        return new JsonResult().OK(map);
//    }
//}
