package com.pateo.qingcloud.authority.repositry;

import com.pateo.qingcloud.authority.domain.rbac.Account;
import com.pateo.qingcloud.authority.support.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账户DB操作
 * @author sean
 * @date 2017/11/2
 */
public interface AccountRepository extends BaseRepository<Account,String> {
    /**
     * 通过用户名查找账户信息
     * @param userName 账户名称
     * @return
     */
    Account findByUserName(String userName);
    @Modifying
    @Transactional
    @Query("update Account a set a.password=?2 where a.id=?1")
    int updatePassword(String accounId, String newPassword);
}
