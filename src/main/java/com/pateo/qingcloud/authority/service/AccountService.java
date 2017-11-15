package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.exception.DBException;
import com.pateo.qingcloud.authority.menu.AccountStatus;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import com.pateo.qingcloud.authority.menu.Sex;
import com.pateo.qingcloud.authority.repositry.AccountRepository;
import com.pateo.qingcloud.authority.repositry.RoleAccountRepository;
import com.pateo.qingcloud.authority.repositry.RoleRepository;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import com.pateo.qingcloud.authority.vo.AccountOut;
import com.pateo.qingcloud.authority.vo.input.AccountVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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

    @Autowired
    private RoleAccountRepository roleAccountRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private MyUserDetailsService myUserDetailsService;



    @Override
    public BaseRepository<Account, String> getBaseDao() {
        return this.accountRepository;
    }

    public Account findUserByLoginName(String userName) {
        return accountRepository.findByUserName(userName);
    }


    /**
     * 创建账号
     *
     * @param accountVo
     * @return
     */
    @Transactional(rollbackFor= Exception.class)
    public void create(AccountVo accountVo) {
        Account account = new Account();
        account.setUsername(accountVo.getUserName());
        account.setAccountWay(accountVo.getAccountWay());
        account.setPassword(passwordEncoder.encode(accountVo.getPassword()));
        account.setStatus(AccountStatus.NOT_ACTIVE);
        accountRepository.save(account);
        //createRoleAdmin(accountVo, account);
    }

    /**
     * 建立角色账户关联
     * @param accountVo 包含角色ID的试图
     * @param account   账户实体
     */
    private void createRoleAdmin(AccountVo accountVo, Account account) {
        if (CollectionUtils.isNotEmpty(account.getRoles())) {
            roleAccountRepository.delete(account.getRoles());
        }
       /* RoleAccount roleAccount = new RoleAccount();
        roleAccount.setRole(roleRepository.getOne(accountVo.getRoleId()));
        roleAccount.setAccount(account);
        roleAccountRepository.save(roleAccount);*/
    }

    /**
     *  删除账号（物理删除）
     * @param id 要删除的账户Id
     * @param projectIds 可以操作的项目Id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteAccount(String id, Set<String> projectIds) throws AccessDeniedException {
        if (!projectIds.contains(find(id).getUser().getProjectId())){
            throw  new AccessDeniedException("权限不足");
        }
        roleAccountRepository.deleteforAccountId(id);
    }

    /**
     * 获取账户详情
     * @param id 账户Id
     * @param projectIds 可以操作的项目Id集合
     * @return
     */
    public AccountOut getInfo(String id, Set<String> projectIds) {
        Account account=find(id);
        if (account==null){
            throw new DBException(ResultEnum.DATA_NOT_EXIST);
        }
        if (account.getUser()!=null&&!projectIds.contains(account.getUser().getProjectId())
                &&!projectIds.contains("0")){
            throw  new AccessDeniedException("权限不足");
        }
        AccountOut accountOut= new AccountOut();
        accountOut.setRealName(account.getUser()==null?"":account.getUser().getRealName());
        accountOut.setEmail(account.getUser()==null?"":account.getUser().getEmail());
        accountOut.setImgUrl(account.getUser()==null?"":account.getUser().getImgUrl());
        accountOut.setPhone(account.getUser()==null?"":account.getUser().getPhone());
        accountOut.setSex(account.getUser()==null? Sex.MALE:account.getUser().getSex());
        accountOut.setUserName(account.getUsername());
        accountOut.setProjectIds(account.getProjectIds());

        return accountOut;
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     */
    @Transactional(rollbackFor=Exception.class)
    public void updatePassword(String accounId, String newPassword) {
        myUserDetailsService.changePassword(accounId,newPassword);
    }

    /**
     * 用户名是否可用
     * @param userName
     * @return
     */
    public boolean isUser(String userName) {
        Account account=new Account();
        account.setUsername(userName);
        return !accountRepository.exists(Example.of(account));
    }

    /**
     * 锁定/解锁 账户
     * @param accountId
     * @param lock
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void locked(String accountId, boolean lock)throws Exception {
        Account account=accountRepository.findOne(accountId);
        if (account==null){
            throw  new DBException(ResultEnum.DATA_NOT_EXIST);
        }
        account.setStatus(lock?AccountStatus.LOCK:AccountStatus.NORMAL);
        accountRepository.save(account);
    }
}

