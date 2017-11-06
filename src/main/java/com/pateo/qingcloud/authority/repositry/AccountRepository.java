package com.pateo.qingcloud.authority.repositry;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.support.BaseRepository;

/**
 * Created by sean on 2017/11/2.
 */
public interface AccountRepository extends BaseRepository<Account,String> {
    Account findByUserName(String loginame);
}
