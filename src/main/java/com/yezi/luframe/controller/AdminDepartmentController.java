package com.yezi.luframe.controller;


import com.yezi.luframe.annotation.RequireLog;
import com.yezi.luframe.entity.AdminDepartment;
import com.yezi.luframe.enums.CodeEnum;
import com.yezi.luframe.service.AdminDepartmentService;
import com.yezi.luframe.util.VOUtils;
import com.yezi.luframe.vo.BaseVO;
import com.yezi.luframe.vo.ExtendVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Desc   : banner管理
 * @author : huangjie
 * @date   : 2018-01-09
 */
@RestController
@SuppressWarnings("all")
@Slf4j
public class AdminDepartmentController {

    @Autowired
    private AdminDepartmentService adminDepartmentService;


    /**
     * 列表查询
     * @return
     */
    @GetMapping("/list/department")
    public ExtendVO listAdminDepartment(){
        try {
            List<AdminDepartment> page=   adminDepartmentService.listAdminDepartment();
            return  VOUtils.returnExtendVOSuccess(page);
        }catch (Exception e){
            log.error("",e);
            return  VOUtils.returnExtendVOError(CodeEnum.ERROR ,"系统错误");
        }
    }

    /**
     * 新增
     * @return
     */
    @RequireLog
    @GetMapping("/insert/department")
    public BaseVO insertDepartment(@RequestParam("deptName") String deptName){
        try {
            adminDepartmentService.insertDepartment(deptName);
            return  VOUtils.returnBaseVOSuccess();
        }catch (Exception e){
            log.error("",e);
            return  VOUtils.returnBaseVOError(CodeEnum.ERROR );
        }
    }


    /**
     * 修改
     * @return
     */
    @GetMapping("/update/department")
    public BaseVO   updateDepartment(@RequestParam("deptName") String deptName,@RequestParam("id") Long id){
        try {
            adminDepartmentService.updateDepartment(deptName,id);
            return  VOUtils.returnBaseVOSuccess();
        }catch (Exception e){
            log.error("",e);
            return  VOUtils.returnBaseVOError(CodeEnum.ERROR );
        }
    }


    /**
     * 删除
     * @return
     */
    @GetMapping("/delete/department")
    public    BaseVO deleteDepartment(@RequestParam("id")  Long id){
        try {
            BaseVO vo= adminDepartmentService.deleteDepartment(id);
            return  vo;
        }catch (Exception e){
            log.error("",e);
            return  VOUtils.returnBaseVOError(CodeEnum.ERROR );
        }
    }


}
