package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.Project;
import com.pateo.qingcloud.authority.exception.DBException;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import com.pateo.qingcloud.authority.repositry.ProjectRepository;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sean
 * 2017/11/3.
 */
@Service
public class ProjectService extends BaseServiceImpl<Project,String> {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public BaseRepository<Project, String> getBaseDao() {
        return this.projectRepository;
    }

    /**
     *
     * @param p
     */
    public void saveOrUpdate(Project p) {
        //修改
        if (StringUtils.isNotBlank(p.getId())){
            p=projectRepository.findOne(p.getId());
            if (StringUtils.isEmpty(p.getId())){
                throw new DBException(ResultEnum.USER_NOT_EXIST);
            }
        }
        projectRepository.save(p);
    }

    public Project findBydel(String id) {
        return projectRepository.findByIdAndDelFlag(id,0);
    }





   /* public ResponseVo findProject(RequestVo requestVo) throws Exception {
        ResponseVo responseVo = new ResponseVo();
        Status status = new Status();
        if (requestVo.getPageVo().getPage() == null || requestVo.getPageVo().getSize() == null
                || requestVo.getPageVo().getPage() == 0 || requestVo.getPageVo().getSize() == 0) {
            status.setCode(Constants.SERVICE_RESPONSE_PAGESIZE_ERROR_CODE);
            status.setDescription(Constants.SERVICE_RESPONSE_PAGESIZE_ERROR_DESC);
            responseVo.setStatus(status);
            return responseVo;
        }
        int pageNum = requestVo.getPageVo().getPage();
        pageNum = pageNum > 0 ? (pageNum - 1) : 0;
        Pageable pageable = new PageRequest(pageNum, requestVo.getPageVo().getSize(), Sort.Direction.ASC, "sid");
        Page<Project> pageQuery = projectDao.findAll(new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> lstPredicates = new ArrayList<Predicate>();
                lstPredicates.add(cb.equal(root.get("del_flag").as(Integer.class), Constants.DelFlg.ENABLE));

                String project_name = requestVo.getProject_name();
                String project_code = requestVo.getProject_code();
                String project_key = requestVo.getProject_key();
                String descr = requestVo.getDescr();

                if (StringUtils.isNotEmpty(project_name)) {
                    lstPredicates.add(cb.like(root.get("project_name").as(String.class), "%" + project_name.trim() + "%"));
                }
                if (StringUtils.isNotEmpty(project_code)) {
                    lstPredicates.add(cb.equal(root.get("project_code").as(String.class), project_code.trim()));
                }
                if (StringUtils.isNotEmpty(project_key)) {
                    lstPredicates.add(cb.equal(root.get("project_key").as(String.class), project_key.trim()));
                }
                if (StringUtils.isNotEmpty(descr)) {
                    lstPredicates.add(cb.like(root.get("descr").as(String.class), "%" + descr.trim() + "%"));
                }
                return cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()]));
            }
        }, pageable);

        List<Project> projectList = pageQuery.getContent();
        List<ProjectVo> projectVoList = new ArrayList<ProjectVo>();
        if (projectList == null || projectList.size() == 0) {
            status.setCode(Constants.DATA_NOT_EXIST_CODE);
            status.setDescription(Constants.DATA_NOT_EXIST_DESC);
            responseVo.setStatus(status);
            return responseVo;
        } else {
            status.setCode(Constants.SERVICE_RESPONSE_SUCCESS_CODE);
            status.setDescription(Constants.SERVICE_RESPONSE_SUCCESS_DESC);
            responseVo.setStatus(status);
        }
        for (Project p : projectList) {
            ProjectVo pVo = new ProjectVo();
            BeanUtils.copyProperties(p, pVo);
            projectVoList.add(pVo);
        }

        PageVo pagination = new PageVo();
        // 当前页
        pagination.setPage(pageNum + 1);
        // 每页显示条数
        pagination.setSize(requestVo.getPageVo().getSize());
        // 总记录数
        pagination.setTotalElements(Integer.parseInt(String.valueOf(pageQuery.getTotalElements())));
        // 总页数
        pagination.setTotalPages(pageQuery.getTotalPages());
        responseVo.setData(projectVoList);
        responseVo.setPageVo(pagination);
        return responseVo;
    }*/



}
