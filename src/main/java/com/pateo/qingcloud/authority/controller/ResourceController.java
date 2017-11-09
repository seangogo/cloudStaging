package com.pateo.qingcloud.authority.controller;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.service.ResourceService;
import com.pateo.qingcloud.authority.vo.input.ResourceVo;
import com.pateo.qingcloud.authority.vo.rbac.ResourceInfo;
import com.pateo.qingcloud.authority.vo.result.DataResult;
import com.pateo.qingcloud.authority.vo.result.StateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


/**
 * @author seangogo
 */
@RestController
@Api(value = "resource", description = "资源模块接口")
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 获取根据角色的所有权限
     * @param account 账户
     * @return
     */
    @GetMapping("/roleTree/{id}")
    @ApiOperation(value = "获取角色资源树", httpMethod = "GET",
            response = DataResult.class, notes = "区分项目Id,超级管理员可以查看所有,普通管理员只能查看单一项目")
    @PreAuthorize("hasPermission(#account,'/resource/roleTree/**_')")
    public ResourceInfo getRoleTree(@AuthenticationPrincipal Account account,
                                    @ApiParam(required = true, name = "id", value = "角色ID")
                                    @PathVariable String id){
        return resourceService.getRoleTree(id,account.getOperableProjectIds());
    }

    /**
     * 获取资源树
     * @param account 账户
     * @return
     */
    @GetMapping
    @ApiOperation(value = "获取自己资源树", httpMethod = "GET", response = DataResult.class, notes = "不区分项目Id")
    public ResourceInfo getOneselfTree(@AuthenticationPrincipal Account account){
        return resourceService.getTree(account.getId());
    }

    /**
     * 获取资源信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取资源详情", httpMethod = "GET", response = DataResult.class, notes = "不区分项目Id")
    public DataResult getInfo(@PathVariable String id){
        return DataResult.success(resourceService.getInfo(id));
    }
    /**
     * 创建/修改资源
     * @param vo
     * @return
     */
    @PostMapping
    @ApiOperation(value = "创建/修改资源", httpMethod = "POST", response = DataResult.class, notes = "创建/修改资源")
    public StateResult save(@RequestBody ResourceVo vo){
        resourceService.save(vo);
        return StateResult.success();
    }

    /**
     * 删除
     * @param id
     */
    @PostMapping("delete/{id}")
    @ApiOperation(value = "删除资源", httpMethod = "POST", response = DataResult.class, notes = "删除资源")
    public StateResult delete(@PathVariable Long id){
        resourceService.delete(id);
        return StateResult.success();
    }

    /**
     * 资源上移/下移
     * @param isUp true=up false=down
     * @param resourceId 资源Id
     * @return result
     */
    @PostMapping("/move/{Id}")
    @ApiOperation(value = "资源上移/下移", httpMethod = "POST", response = DataResult.class, notes = "资源上移/下移")
    public StateResult moveUp(
            @ApiParam(required = true, name = "isUp", value = "上移，反之下移动")@RequestParam boolean isUp,
            @ApiParam(required = true, name = "resourceId", value = "资源Id")@PathVariable String resourceId){
        resourceService.move(resourceId, isUp);
        return StateResult.success();
    }

}
