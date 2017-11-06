package com.pateo.qingcloud.authority.domain;

import com.pateo.qingcloud.authority.support.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_project")
public class Project extends BaseEntity<Long>{
    /**
     * 项目标题
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 项目内容
     */
    @Column(name = "project_code")
    private String projectCode;
}
