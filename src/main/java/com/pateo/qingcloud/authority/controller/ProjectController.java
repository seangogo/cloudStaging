package com.pateo.qingcloud.authority.controller;

import com.pateo.qingcloud.authority.domain.Project;
import com.pateo.qingcloud.authority.service.ProjectService;
import com.pateo.qingcloud.authority.vo.result.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sean
 * 2017/11/3.
 */
@RestController
@RequestMapping("project")
@Api(value = "project", description = "项目模块接口")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @RequestMapping(value = "/getAll", method = RequestMethod.POST, produces="application/json")
    @ApiOperation(value = "项目列表", httpMethod = "POST", response = DataResult.class, notes = "获取项目列表")
    public Project getAll(@ApiParam(required = false, name = "projectVo", value = "品牌列表查询条件") @RequestBody Project project) {

        return null;

    }
}
