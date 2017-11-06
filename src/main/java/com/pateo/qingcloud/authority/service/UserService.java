package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.User;
import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.menu.AccountStatus;
import com.pateo.qingcloud.authority.menu.AccountWay;
import com.pateo.qingcloud.authority.repositry.AccountRepository;
import com.pateo.qingcloud.authority.repositry.UserRepository;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import com.pateo.qingcloud.authority.vo.input.UserSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


/**
 * @author sean
 * 2017/11/2.
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseServiceImpl<User,String>{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public BaseRepository<User, String> getBaseDao() {
        return this.userRepository;
    }

    /**
     * 添加用户
     * @param userSaveVo 前端数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserSaveVo userSaveVo, Set<String> projectIds)
            throws  AccessDeniedException{
        if (projectIds.size()!=1){
            throw  new AccessDeniedException("管理员只能有一个项目");
        }
        String id="";
        for (String s:projectIds){
            id=s;
        }
        User user = new User();
        user.setEmail(userSaveVo.getEmail());
        user.setImgUrl(userSaveVo.getImgUrl());
        user.setPhone(userSaveVo.getPhone());
        user.setProjectId(id);
        user.setRealName(userSaveVo.getRealName());
        user.setSex(userSaveVo.getSex());
        userRepository.save(user);
        //创建账户
        Account account = new Account();
        account.setUsername(userSaveVo.getUserName());
        account.setAccountWay(AccountWay.MANUAL);
        account.setPassword(passwordEncoder.encode(userSaveVo.getPassword()));
        account.setStatus(AccountStatus.NOT_ACTIVE);
        account.setUser(user);
        accountRepository.save(account);
    }
}
