package com.pateo.qingcloud.authority.domain;

import com.pateo.qingcloud.authority.menu.OpenStatus;
import com.pateo.qingcloud.authority.support.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_Organize")
public class Organize extends BaseEntity<String>{
    /**
     * 项目id
     */
    private String projectId;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织名称
     */
    private String orgDesc;

    /**
     * 组织级别
     */
    private String orgLevel;

    /**
     * 上级组织id
     */
    private String parentId;

    /**
     * 排序序号
     */
    private String seqNo;

    /**
     * 组织状态 0-启用 1-禁用
     */
    private OpenStatus orgStatus;
}
