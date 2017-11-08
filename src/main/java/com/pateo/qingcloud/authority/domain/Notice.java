package com.pateo.qingcloud.authority.domain;

import com.pateo.qingcloud.authority.menu.OpenStatus;
import com.pateo.qingcloud.authority.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Getter
@Setter
public class Notice  extends BaseEntity<String>{
    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告状态 0-启用 1-禁用
     */
    private OpenStatus openStatus;
}
