package com.pateo.qingcloud.authority.controller;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.domain.rbac.Resource;
import com.pateo.qingcloud.authority.service.ResourceService;
import com.pateo.qingcloud.authority.utils.QueryResultConverter;
import com.pateo.qingcloud.authority.vo.input.ResourceSelectVo;
import com.pateo.qingcloud.authority.vo.input.ResourceVo;
import com.pateo.qingcloud.authority.vo.rbac.ResourceInfo;
import com.pateo.qingcloud.authority.vo.result.DataResult;
import com.pateo.qingcloud.authority.vo.result.StateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
     * 删除(物理删除)
     * @param id
     */
    @PostMapping("delete/{id}")
    @ApiOperation(value = "删除资源", httpMethod = "POST", response = DataResult.class,
            notes = "删除资源")
    public StateResult delete(@PathVariable String id){
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

    /**
     *  分页动态查询
     * @param pageable
     * @param account
     * @param resourceSelectVo
     * @return
     */
    @PostMapping(value = "/list")
    @ApiOperation(value = "资源动态分页", httpMethod = "POST", response = DataResult.class,
            notes = "模糊查询：code，name 匹配：parent ,type")
    public DataResult getUserList(@PageableDefault(sort = "sort",direction = Sort.Direction.ASC) Pageable pageable,
                                  @AuthenticationPrincipal Account account,
                                  @ApiParam(name = "ResourceSelectVo", value = "资源查询条件")
                                  @RequestBody ResourceSelectVo resourceSelectVo){
        Resource resource=new Resource();
        BeanUtils.copyProperties(resourceSelectVo,resource);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("code", match -> match.contains())
                .withMatcher("name", match -> match.contains());
        if (StringUtils.isNotBlank(resourceSelectVo.getParent())){
            Resource parent=new Resource();
            parent.setId(resourceSelectVo.getParent());
            resource.setParent(parent);
        }
        Example<Resource> example = Example.of(resource, matcher);
        Page<Resource> resources=resourceService.findAll(example,pageable);
        List<ResourceVo> resourceVos= QueryResultConverter.convert(resources.getContent(),ResourceVo.class);
        return DataResult.success(resourceVos);
    }

}
