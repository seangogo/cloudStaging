package com.pateo.qingcloud.authority.controller;

import com.pateo.qingcloud.authority.domain.Project;
import com.pateo.qingcloud.authority.service.ProjectService;
import com.pateo.qingcloud.authority.vo.input.ProjectSaveVo;
import com.pateo.qingcloud.authority.vo.input.ProjectSelectVo;
import com.pateo.qingcloud.authority.vo.result.DataResult;
import com.pateo.qingcloud.authority.vo.result.StateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author sean
 * 2017/11/3.
 */
@RestController
@RequestMapping("/project")
@Api(description = "项目微服务")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/saveorUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "新建项目", httpMethod = "POST", response = StateResult.class, notes = "新建项目")
    public StateResult savePasswordStrategy(
            @ApiParam(required = true, name = "projectCreateVo", value = "新建项目")
            @Valid @RequestBody ProjectSaveVo projectCreateVo , BindingResult bindingResult) {
            Project p =  new Project();
            BeanUtils.copyProperties(projectCreateVo, p);
            projectService.saveOrUpdate(p);
        return StateResult.success();
    }

    /**
     * 只有超级管理员才有此权限
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "删除项目", httpMethod = "POST", response = StateResult.class, notes = "删除项目")
    public StateResult delProject(@PathVariable String id) {
        projectService.delete(id);
        return StateResult.success();
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询项目详情", httpMethod = "GET", response = DataResult.class, notes = "查询项目详情")
    public DataResult find(
            @PathVariable("id") String id) {
        return DataResult.success(projectService.findBydel(id));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询项目(不分页)", httpMethod = "GET", response = DataResult.class, notes = "查询项目(不分页)")
    public DataResult findAll() {
      return DataResult.success(projectService.findAll());
    }

    @RequestMapping(value = "/findproject", method = RequestMethod.POST)
    @ApiOperation(value = "查询项目列表分页", httpMethod = "POST", response = DataResult.class, notes = "查询项目列表分页")
    public DataResult findproject(@PageableDefault Pageable pageable,
            @ApiParam(name = "projectSelectVo", value = "查询项目列表分页")
            @RequestBody ProjectSelectVo projectSelectVo) {

        Project project=new Project();
        BeanUtils.copyProperties(projectSelectVo,project);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("projectName", match -> match.contains())
                .withMatcher("projectCode", match -> match.ignoreCase())
                .withMatcher("projectKey", match -> match.ignoreCase())
                .withMatcher("desrc", match -> match.contains());
            Example<Project> example = Example.of(project, matcher);
        return DataResult.success(projectService.findAll(example,pageable));
    }

}
