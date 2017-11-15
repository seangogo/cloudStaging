package com.pateo.qingcloud.authority.domain;

import com.pateo.qingcloud.authority.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_project")
@Setter
@Getter
public class Project extends BaseEntity<String>{
    /**
     * 项目标题
     */
    private String projectName;

    /**
     * 项目代号
     */
    @Column(unique = true)
    private String projectCode;

    /**
     * 项目KEY
     */
    private String projectKey;

    /**
     * 项目描述
     */
    private String descr;
}
