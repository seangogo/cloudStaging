package com.pateo.qingcloud.authority.controller;

import com.pateo.qingcloud.authority.domain.User;
import com.pateo.qingcloud.authority.service.UserService;
import com.pateo.qingcloud.authority.vo.result.Status;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sean
 * 2017/11/2.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * @param userSaveVo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "返回状态", httpMethod = "POST", response = Status.class, notes = "返回状态")
    public Status addUser(@ApiParam(required = true, name = "userSaveVo", value = "用户添加vo")
                              @RequestBody User userSaveVo) {
        //添加用户
        userService.save(userSaveVo);

        return Status.success();

    }



}
