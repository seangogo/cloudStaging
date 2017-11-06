package com.pateo.qingcloud.authority.repositry;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.support.BaseRepository;

/**
 * 账户DB操作
 * @author sean
 * @date 2017/11/2
 */
public interface AccountRepository extends BaseRepository<Account,Long> {
    /**
     * 通过用户名查找账户信息
     * @param userName 账户名称
     * @return
     */
    Account findByUserName(String userName);
}
