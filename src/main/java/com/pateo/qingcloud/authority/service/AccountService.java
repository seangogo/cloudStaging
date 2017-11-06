package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.repositry.AccountRepository;
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
    private AccountRepository accountRepository;
    @Override
    public BaseRepository<Account, String> getBaseDao() {
        return this.accountRepository;
    }

    public Account findUserByLoginName(String userName){
        return  accountRepository.findByUserName(userName);
    }
}
