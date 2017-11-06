package com.pateo.qingcloud.authority.repositry;

import com.pateo.qingcloud.authority.domain.rbac.Resource;
import com.pateo.qingcloud.authority.support.BaseRepository;

/**
 * Created by sean on 2017/11/6.
 */
public interface ResourceRepository extends BaseRepository<Resource,Long> {
    Resource findByName(String name);
}
