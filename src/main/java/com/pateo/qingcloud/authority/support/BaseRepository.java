package com.pateo.qingcloud.authority.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * JpaRepository接口提供crud和分页接口
 * JpaSpecificationExecutor提供Specification查询接口
 * @NoRepositoryBean让jpa:repositories扫描时忽略
 * @param <T>
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID extends Serializable>
        extends JpaRepository<T, Serializable>, JpaSpecificationExecutor<T> {

    T findByIdAndDelFlag(ID id, int i);
}
