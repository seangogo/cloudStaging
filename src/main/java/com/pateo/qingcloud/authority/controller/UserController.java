package com.pateo.qingcloud.authority.controller;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.service.UserService;
import com.pateo.qingcloud.authority.vo.input.UserSaveVo;
import com.pateo.qingcloud.authority.vo.result.Status;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author sean
 * 2017/11/2.
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login")
    @ApiOperation(value = "登陆账号", httpMethod = "POST", response = Status.class, notes = "返回状态")
    public Status login(@RequestParam String name,
                        @RequestParam String password ) {
        log.info("账号：{}，密码：{}",name,password);
        return Status.success();

    }



    @GetMapping(value = "/list")
    @ApiOperation(value = "用户列表", httpMethod = "GET", response = Status.class, notes = "获取用户列表")
    public Status getUserList(@PageableDefault Pageable pageable,Authentication authentication
            /*@ApiParam(name = "userListSearchVo", value = "用户列表查询条件")
            @RequestBody  userListSearchVo*/){
        Account account=(Account) authentication.getPrincipal();
        System.out.println(account.getProjectIds());
        return Status.success(userService.findAll(pageable));
    }




    @PostMapping(value = "/save")
    @ApiOperation(value = "返回状态", httpMethod = "POST", response = Status.class, notes = "返回状态")
    public Status addUser(@ApiParam(required = true, name = "userSaveVo", value = "添加用户")
                              @Valid @RequestBody UserSaveVo userSaveVo, BindingResult errors,
                          @AuthenticationPrincipal Account account){

        log.info("前端参数:{}",userSaveVo.toString());
        userService.addUser(userSaveVo,account.getProjectIds());
        return Status.success();

    }
}
