package com.pateo.qingcloud.authority.controller;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.domain.rbac.Role;
import com.pateo.qingcloud.authority.exception.DBException;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import com.pateo.qingcloud.authority.service.RoleService;
import com.pateo.qingcloud.authority.utils.QueryResultConverter;
import com.pateo.qingcloud.authority.vo.input.RoleSelectVo;
import com.pateo.qingcloud.authority.vo.input.RoleVo;
import com.pateo.qingcloud.authority.vo.result.DataResult;
import com.pateo.qingcloud.authority.vo.result.StateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author sean
 * @date 2017/11/8
 */
@Slf4j
@RestController
@Api(value = "role", description = "角色模块接口")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 获取角色信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取资源详情", httpMethod = "GET", response = DataResult.class, notes = "不区分项目Id")
    public DataResult getInfo(@PathVariable(value = "id") String id, @AuthenticationPrincipal Account account) throws Exception {
        RoleVo roleVo=roleService.getInfo(id);
        if (!account.getProjectIds().contains(roleVo.getProjectId())){
            throw new DBException(ResultEnum.PROJECTIDID_NOT_EXIST);
        }
        return DataResult.success(roleVo);
    }




    /**
     * 创建/修改角色
     *
     * @param roleVo
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建/修改角色", httpMethod = "POST", response = DataResult.class, notes = "创建/修改角色")
    public StateResult save(@ApiParam(required = true, name = "roleVo", value = "角色VO")
                            @Valid @RequestBody RoleVo roleVo, BindingResult errors,
                            @AuthenticationPrincipal Account account) {
        log.info("nbool:{}",account.getProjectIds().contains("0"));
        if (!account.getOperableProjectIds().contains(roleVo.getProjectId())&&
                !account.getProjectIds().contains("0")){
            return StateResult.error(ResultEnum.PROJECTIDID_NOT_EXIST);
        }
        roleService.save(roleVo);
        return StateResult.success();
    }

    /**
     *  分页动态查询
     * @param pageable
     * @param account
     * @param roleSelectVo
     * @return
     */
    @PostMapping(value = "/list")
    @ApiOperation(value = "角色动态分页", httpMethod = "POST", response = DataResult.class,
            notes = "模糊查询：code，name，匹配：projectId")
    public DataResult getUserList(@PageableDefault Pageable pageable,
                                  @AuthenticationPrincipal Account account,
                                  @ApiParam(name = "roleSelectVo", value = "角色列表查询条件")
                                  @RequestBody RoleSelectVo roleSelectVo){
        if (account.getProjectIds().contains("0")&& StringUtils.isNotBlank(roleSelectVo.getProjectId())){
            throw new DBException(ResultEnum.PROJECTIDID_NOT_EXIST);
        }
        Role role=new Role();
        BeanUtils.copyProperties(roleSelectVo,role);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.contains())
                .withMatcher("code", match -> match.contains());

        Example<Role> example = Example.of(role, matcher);
        Page<Role> roles=roleService.findAll(example,pageable);
        List<RoleVo> roleVos= QueryResultConverter.convert(roles.getContent(),RoleVo.class);
        return DataResult.success(roleVos);
    }


    /**
     * 删除角色
     * @param id
     * @param account
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/delete/{id}")
    @PreAuthorize("hasPermission(#account,'/role/delete/**_')")
    @ApiOperation(value = "删除角色数据", httpMethod = "POST", response = DataResult.class,
            notes = "删除用户，如与任何实体关联将不能删除")
    public StateResult delete(@PathVariable String id,
                              @AuthenticationPrincipal Account account) throws Exception {
        roleService.deleteRole(id,account.getOperableProjectIds());
        return StateResult.success();
    }

    /**
     * 账户绑定角色
     * @param accountId
     * @param account
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/{accountId}/bind/{roleId}")
    @PreAuthorize("hasPermission(#account,'/role/**/bind/**_')")
    @ApiOperation(value = "账户绑定角色", httpMethod = "POST", response = DataResult.class,
            notes = "账户绑定角色")
    public StateResult bind(@PathVariable String accountId,@PathVariable String roleId,
                              @AuthenticationPrincipal Account account) throws Exception {
        roleService.bindAccount(accountId,roleId,account.getOperableProjectIds());
        return StateResult.success();
    }

}
