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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


/**
 * @author seangogo
 */
@RestController
@Api(value = "resource", description = "资源相关接口")
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 获取资源树
     * @param account 账户
     * @return
     */
    @GetMapping
    @ApiOperation(value = "获取资源树", httpMethod = "GET", response = DataResult.class, notes = "不区分项目Id")
    public ResourceInfo getTree(@AuthenticationPrincipal Account account){
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
