package com.pateo.qingcloud.authority.controller;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import com.pateo.qingcloud.authority.service.RoleService;
import com.pateo.qingcloud.authority.vo.input.RoleVo;
import com.pateo.qingcloud.authority.vo.result.DataResult;
import com.pateo.qingcloud.authority.vo.result.StateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 * @author sean
 * @date 2017/11/8
 */
@Slf4j
@RestController
@Api(value = "role", description = "角色模块相关接口")
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
   /* @GetMapping("/{id}")
    @ApiOperation(value = "获取资源详情", httpMethod = "GET", response = DataResult.class, notes = "不区分项目Id")
    public DataResult getInfo(@PathVariable String id) {
        return DataResult.success(resourceService.getInfo(id));
    }*/

    /**
     * 创建/修改角色
     *
     * @param roleVo
     * @return
     */
    @PostMapping
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



}
