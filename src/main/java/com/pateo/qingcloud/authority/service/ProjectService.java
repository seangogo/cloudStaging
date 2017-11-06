package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.Project;
import com.pateo.qingcloud.authority.repositry.ProjectRepository;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sean
 * 2017/11/3.
 */
@Service
public class ProjectService extends BaseServiceImpl<Project,Long> {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public BaseRepository<Project, Long> getBaseDao() {
        return this.projectRepository;
    }



}
