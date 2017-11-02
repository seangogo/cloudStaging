package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.Account;
import com.pateo.qingcloud.authority.repositry.AccountRepositry;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sean on 2017/11/2.
 */
@Service
public class AccountService extends BaseServiceImpl<Account,String> {
    @Autowired
    private AccountRepositry accountRepositry;
    @Override
    public BaseRepository<Account, String> getBaseDao() {
        return this.accountRepositry;
    }

    public Account findUserByLoginName(String loginName){
        return  accountRepositry.findByLoginName(loginName);
    }
}
