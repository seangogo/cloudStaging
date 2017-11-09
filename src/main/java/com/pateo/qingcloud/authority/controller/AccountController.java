package com.pateo.qingcloud.authority.controller;


import com.pateo.qingcloud.authority.domain.User;
import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import com.pateo.qingcloud.authority.service.AccountService;
import com.pateo.qingcloud.authority.service.MyUserDetailsService;
import com.pateo.qingcloud.authority.utils.QueryResultConverter;
import com.pateo.qingcloud.authority.vo.AccountOut;
import com.pateo.qingcloud.authority.vo.input.AccountSelectVo;
import com.pateo.qingcloud.authority.vo.input.AccountVo;
import com.pateo.qingcloud.authority.vo.result.DataResult;
import com.pateo.qingcloud.authority.vo.result.SimpleResponse;
import com.pateo.qingcloud.authority.vo.result.StateResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     *  分页动态查询
     * @param pageable 默认的分页对象
     * @param account  账号对象
     * @param accountSelectVo 查询条件
     * @return
     */
    @PostMapping(value = "/list")
    @ApiOperation(value = "账户动态分页", httpMethod = "POST", response = DataResult.class,
            notes = "模糊查询：realName，email，idcard,phone，匹配：sex")
    public DataResult getUserList(@PageableDefault Pageable pageable,
                                  @AuthenticationPrincipal Account account,
                                  @ApiParam(name = "UserSelectVo", value = "用户列表查询条件")
                                  @RequestBody AccountSelectVo accountSelectVo){
        Specification querySpecifi = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(accountSelectVo.getCreatedDateStart()!=null){
                predicates.add(criteriaBuilder.greaterThan(root.get("createdDate"),
                        accountSelectVo.getCreatedDateStart()));
            }
            if(null != accountSelectVo.getCreatedDateStart()){
                predicates.add(criteriaBuilder.lessThan(root.get("createdDate"),
                        accountSelectVo.getCreatedDateEnd()));
            }
            if(null != accountSelectVo.getUserName()){
                predicates.add(criteriaBuilder.like(root.get("userName").as(String.class), "%"+accountSelectVo.getUserName()+"%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));


        };
        Page<Account> accounts=accountService.findAll(querySpecifi,pageable);
        return DataResult.success(QueryResultConverter.convert(accounts, AccountVo.class, pageable));
    }

    /**
     * 获取当前登录的管理员信息
     * @param account 认证信息
     * @return
     */
    @SuppressWarnings("AlibabaAvoidApacheBeanUtilsCopy")
    @GetMapping("/authenticationInfo")
    @ApiOperation(value = "登陆用户信息", httpMethod = "GET", response = DataResult.class, notes = "获取当前登录的管理员信息")
    public AccountOut me(@AuthenticationPrincipal @ApiParam(hidden = true) Account account)
            throws InvocationTargetException, IllegalAccessException {
        AccountOut accountOut = new AccountOut();
        accountOut.setUserName(account.getUsername());
        User user=account.getUser();
        BeanUtils.copyProperties(user,accountOut);
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

    /**
     * 修改密码
     * @return
     */
    @PostMapping("password/modify")
    @ApiOperation(value = "修改密码", httpMethod = "POST",
            response = DataResult.class, notes = "修改密码")
    public StateResult updatePassword(@AuthenticationPrincipal Account account,
                                      @ApiParam(name = "oldPassword", value = "旧密码")@RequestParam String oldPassword,
                                      @ApiParam(name = "newPassword", value = "新密码")@RequestParam String newPassword){
        if (passwordEncoder.matches(account.getPassword(),oldPassword)){
            return StateResult.error(ResultEnum.PASSWORD_NOT_MATCHING);
        }
        myUserDetailsService.changePassword(account.getId(),passwordEncoder.encode(newPassword));
        return StateResult.success();
    }


}
