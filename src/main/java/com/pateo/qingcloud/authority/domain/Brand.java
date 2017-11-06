package com.pateo.qingcloud.authority.domain;

import com.pateo.qingcloud.authority.support.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_brand")
@Getter
@Setter
public class Brand extends BaseEntity<String>{
    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌编码
     */
    private String code;
}
