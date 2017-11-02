package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.User;
import com.pateo.qingcloud.authority.repositry.UserRepositry;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author sean
 * 2017/11/2.
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseServiceImpl<User,String>{
    @Autowired
    private UserRepositry userRepositry;

    @Override
    public BaseRepository<User, String> getBaseDao() {
        return this.userRepositry;
    }



}
