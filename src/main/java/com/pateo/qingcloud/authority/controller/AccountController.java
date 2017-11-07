package com.pateo.qingcloud.authority.controller;


import com.pateo.qingcloud.authority.domain.User;
import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.service.AccountService;
import com.pateo.qingcloud.authority.vo.AccountOut;
import com.pateo.qingcloud.authority.vo.input.AccountVo;
import com.pateo.qingcloud.authority.vo.result.DataResult;
import com.pateo.qingcloud.authority.vo.result.SimpleResponse;
import com.pateo.qingcloud.authority.vo.result.StateResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

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
     * 获取当前登录的管理员信息
     * @param account 认证信息
     * @return
     */
    @SuppressWarnings("AlibabaAvoidApacheBeanUtilsCopy")
    @GetMapping("/me")
    public AccountOut me(@AuthenticationPrincipal @ApiParam(hidden = true) Account account)
            throws InvocationTargetException, IllegalAccessException {
        AccountOut accountOut = new AccountOut();
        accountOut.setUserName(account.getUsername());
        User user=account.getUser();
        BeanUtils.copyProperties(accountOut, user);
        return accountOut;
    }

    /**
     * 新增账户 必须关联角色 用户
     * @param accountVo
     * @param errors
     * @return
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增账户", httpMethod = "POST", response = DataResult.class, notes = "返回状态")
    public SimpleResponse addAccount(@ApiParam(required = true, name = "accountVo", value = "账户模型")
                          @Valid @RequestBody AccountVo accountVo, BindingResult errors){
        log.info("前端参数:{}",accountVo.toString());
        accountService.create(accountVo);
        return new SimpleResponse(StateResult.success());
    }


    /**
     * 删除账号(逻辑删除)
     * @param id 账户ID
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除账号(逻辑删除)", httpMethod = "POST",
            response = DataResult.class, notes = "连带删除关联的角色（物理删除)")
    public void delete(@ApiParam(hidden = true)@AuthenticationPrincipal Account account
            ,@ApiParam(required = true, name = "id", value = "账户ID")@RequestParam String id) {
        Set<String> projectIds=account.getProjectIds();
        accountService.deleteAccount(id,projectIds);
    }

    /**
     * 查询账户详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public AccountOut getInfo(@ApiParam(hidden = true)@AuthenticationPrincipal Account account
                              ,@PathVariable String id) {
        Set<String> projectIds=account.getProjectIds();
        return accountService.getInfo(id,projectIds);
    }


   /* @GetMapping
    public Page<AdminInfo> query(AdminCondition condition, Pageable pageable) {
        return adminService.query(condition, pageable);
    }
*/


}
