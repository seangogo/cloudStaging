package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.repositry.RoleAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sean
 * @date 2017/11/6
 */
@Service
public class RoleAccountService {
    @Autowired
    private RoleAccountRepository roleAccountRepository;

}
