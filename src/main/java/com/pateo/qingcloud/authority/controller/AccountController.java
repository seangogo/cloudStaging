package com.pateo.qingcloud.authority.controller;


import com.pateo.qingcloud.authority.service.AccountService;
import com.pateo.qingcloud.authority.vo.input.AccountVo;
import com.pateo.qingcloud.authority.vo.result.Status;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 账户
 * @author seangogo
 */
@RestController
@RequestMapping("account")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;


    /**
     * 新增账户
     * @param accountVo
     * @param errors
     * @return
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增账户", httpMethod = "POST", response = Status.class, notes = "返回状态")
    public Status addAccount(@ApiParam(required = true, name = "accountVo", value = "账户模型")
                          @Valid @RequestBody AccountVo accountVo, BindingResult errors){
        log.info("前端参数:{}",accountVo.toString());
        accountService.create(accountVo);
        return Status.success();

    }



}
