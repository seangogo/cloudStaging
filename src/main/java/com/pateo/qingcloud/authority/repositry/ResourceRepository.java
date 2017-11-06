package com.pateo.qingcloud.authority.repositry;

import com.pateo.qingcloud.authority.domain.rbac.Resource;
import com.pateo.qingcloud.authority.support.BaseRepository;

/**
 * 资源相关数据操作
 * @author sean
 * @date 2017/11/6
 */
public interface ResourceRepository extends BaseRepository<Resource,String> {
    /**
     * 通过资源名称查找资源
     * @param name 资源名称
     * @return
     */
    Resource findByName(String name);
}
