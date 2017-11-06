package com.pateo.qingcloud.authority.repositry;

import com.pateo.qingcloud.authority.domain.rbac.RoleAccount;
import com.pateo.qingcloud.authority.support.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


/**
 * @author seangogo
 */
public interface RoleAccountRepository extends BaseRepository<RoleAccount,String> {

    @Modifying
    @Transactional
    @Query(value = "DELETE  from RoleAccount a where a.account.id=?1")
    int deleteforAccountId(String id);
}
