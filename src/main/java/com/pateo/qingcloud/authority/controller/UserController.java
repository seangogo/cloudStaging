package com.pateo.qingcloud.authority.controller;

import com.pateo.qingcloud.authority.domain.User;
import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.menu.Sex;
import com.pateo.qingcloud.authority.service.UserService;
import com.pateo.qingcloud.authority.vo.input.UserSaveVo;
import com.pateo.qingcloud.authority.vo.input.UserSelectVo;
import com.pateo.qingcloud.authority.vo.result.DataResult;
import com.pateo.qingcloud.authority.vo.result.StateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(value = "resource", description = "用户模块接口")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login")
    @ApiOperation(value = "登陆账号", httpMethod = "POST", response = DataResult.class, notes = "返回状态")
    public StateResult login(@RequestParam String name,
                                @RequestParam String password ) {
        log.info("账号：{}，密码：{}",name,password);

        return StateResult.success();

    }


    /**
     *  分页动态查询
     * @param pageable
     * @param account
     * @param userSelectVo
     * @return
     */
    @PostMapping(value = "/list")
    @ApiOperation(value = "用户动态分页", httpMethod = "POST", response = DataResult.class,
            notes = "模糊查询：realName，email，idcard,phone，匹配：sex")
    public DataResult getUserList(@PageableDefault Pageable pageable,
            @AuthenticationPrincipal Account account,
            @ApiParam(name = "UserSelectVo", value = "用户列表查询条件")
            @RequestBody UserSelectVo userSelectVo){
        User user=new User();
        BeanUtils.copyProperties(userSelectVo,user);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("realName", match -> match.contains())
                .withMatcher("email", match -> match.contains())
                .withMatcher("idCard", match -> match.contains())
                .withMatcher("phone", match -> match.contains());
        if (userSelectVo.getSex()<1){
            user.setSex(userSelectVo.getSex()==0?Sex.MALE:Sex.FEMALE);
        }
        Example<User> example = Example.of(user, matcher);
        return DataResult.success(userService.findAll(example,pageable));
    }


    /**
     * 新增/修改用户
     * @param userSaveVo
     * @param errors
     * @param account
     * @return
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增/修改用户", httpMethod = "POST", response = DataResult.class, notes = "新增/修改用户不与任何实体关联" +
            "新增不传project或者传空字符串")
    public StateResult addUser(@ApiParam(required = true, name = "userSaveVo", value = "用户VO")
                              @Valid @RequestBody UserSaveVo userSaveVo, BindingResult errors,
                              @AuthenticationPrincipal Account account) throws Exception {
        userService.modifiedUser(userSaveVo,account.getProjectIds());
        return StateResult.success();
    }

    /**
     * 删除用户
     * @param id
     * @param account
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除用户数据", httpMethod = "POST", response = DataResult.class,
            notes = "删除用户，如与任何实体关联将不能删除")
    public StateResult delete(@PathVariable String id,
                              @AuthenticationPrincipal Account account) throws Exception {
        userService.deleteUser(id,account.getProjectIds());
        return StateResult.success();
    }

    /**
     * 用户详情
     * @param id
     * @param account
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasPermission(#account,'user/delete_')")
    @ApiOperation(value = "查询用户详情", httpMethod = "GET", response = DataResult.class,
            notes = "用户详情信息，目前只有用户信息，之后补上对应账户角色")
    public DataResult details(@PathVariable(value ="id") String id,
                              @AuthenticationPrincipal Account account) throws Exception {
        return DataResult.success(userService.details(id,account.getProjectIds()));
    }


}
