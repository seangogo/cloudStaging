package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.repositry.AccountRepository;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import com.pateo.qingcloud.authority.vo.input.AccountVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账号服务
 * @author sean
 * @date 2017/11/2
 */
@SuppressWarnings("ALL")
@Service
@Transactional(readOnly = true)
public class AccountService extends BaseServiceImpl<Account,String> {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BaseRepository<Account, String> getBaseDao() {
        return this.accountRepository;
    }

    public Account findUserByLoginName(String userName){
        return  accountRepository.findByUserName(userName);
    }

    /**
     * 创建账号
     * @param accountVo
     * @return
     */
    @Transactional
    public AccountVo create(AccountVo accountVo) {

        Account account = new Account();
        BeanUtils.copyProperties(accountVo, account);
        //密码
        account.setPassword(passwordEncoder.encode(account.getPassword()));



        accountRepository.save(account);
      /*  accountVo.setId(admin.getId());

        createRoleAdmin(adminInfo, admin);*/

        return accountVo;
    }

    /**
     * 修改管理员
     * @param adminInfo
     * @return
     *//*
    public AdminInfo update(AdminInfo adminInfo) {

        Admin admin = adminRepository.findOne(adminInfo.getId());
        BeanUtils.copyProperties(adminInfo, admin);

        createRoleAdmin(adminInfo, admin);

        return adminInfo;
    }

    *//**
     * 删除管理员
     * @param id
     *//*
    private void createRoleAdmin(AdminInfo adminInfo, Admin admin) {
        if(CollectionUtils.isNotEmpty(admin.getRoles())){
            roleAdminRepository.delete(admin.getRoles());
        }
        RoleAdmin roleAdmin = new RoleAdmin();
        roleAdmin.setRole(roleRepository.getOne(adminInfo.getRoleId()));
        roleAdmin.setAdmin(admin);
        roleAdminRepository.save(roleAdmin);
    }

    *//**
     * 获取管理员详细信息
     * @param id
     * @return
     *//*
    public void delete(Long id) {
        adminRepository.delete(id);
    }

    *//**
     * 分页查询管理员
     * @param condition
     * @return
     *//*
    public AdminInfo getInfo(Long id) {
        Admin admin = adminRepository.findOne(id);
        AdminInfo info = new AdminInfo();
        BeanUtils.copyProperties(admin, info);
        return info;
    }

    *//* (non-Javadoc)
     * @see com.imooc.security.rbac.service.AdminService#query(com.imooc.security.rbac.dto.AdminInfo, org.springframework.data.domain.Pageable)
     *//*
    public Page<AdminInfo> query(AdminCondition condition, Pageable pageable) {
        Page<Admin> admins = adminRepository.findAll(new AdminSpec(condition), pageable);
        return QueryResultConverter.convert(admins, AdminInfo.class, pageable);
    }*/

}
