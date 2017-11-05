package com.pateo.qingcloud.authority.support;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * 实体基类
 * @author sean
 * @param <U> 用户ID
 */
@MappedSuperclass
@EqualsAndHashCode(exclude = "sid")
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<U> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID自动生成策略
     */
    @Id
    @GeneratedValue
    private Long id;


    /**
     * 创建人 登录帐号
     */
    @CreatedBy
    @Column(name = "create_by",updatable = false )
    protected U createdBy;

    /**
     * 修改人 登录的帐号
     */
    @LastModifiedBy
    @Column(name = "update_by")
    protected U updateBy;

    /**
     * 创建时间
     */
    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "create_date",updatable = false)
    protected Date createdDate;
    /**
     * 修改时间
     */
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "update_date",insertable = false)
    protected Date updatedDate;

    /**
     * 删除标志 0 - 正常 1 - 删除
     */
    @Column(name = "del_flag")
    private Integer delFlag=0;

    /**
     * 版本号
     */
    @Version
    @Column(name = "version")
    protected Integer version;
}