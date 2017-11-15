package com.pateo.qingcloud.authority.service;

import com.pateo.qingcloud.authority.domain.User;
import com.pateo.qingcloud.authority.exception.DBException;
import com.pateo.qingcloud.authority.menu.ResultEnum;
import com.pateo.qingcloud.authority.repositry.UserRepository;
import com.pateo.qingcloud.authority.support.BaseRepository;
import com.pateo.qingcloud.authority.support.BaseServiceImpl;
import com.pateo.qingcloud.authority.vo.input.UserSaveVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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

    @Override
    public BaseRepository<User, String> getBaseDao() {
        return this.userRepository;
    }

    /**
     * 添加用户
     * @param userSaveVo 前端数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void modifiedUser(UserSaveVo userSaveVo, Set<String> projectIds)
            throws  Exception{
        if (projectIds.size()!=1){
            throw  new AccessDeniedException("管理员只能有一个项目");
        }
        User user = new User();
        //修改
        if (StringUtils.isNotBlank(userSaveVo.getId())){
                user=userRepository.findOne(userSaveVo.getId());
                if (StringUtils.isEmpty(user.getId())){
                throw new DBException(ResultEnum.USER_NOT_EXIST);
                }
        }else{
            String id="";
            for (String s:projectIds){
                id=s;
            }
            user.setProjectId(id);
        }
        BeanUtils.copyProperties(userSaveVo, user);
        userRepository.save(user);
    }

    /**
     * 删除用户
     * @param id
     * @param projectIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(String id, Set<String> projectIds)throws DBException {
        if (userRepository.exists(id)){
           User user=userRepository.findOne(id);
            if (projectIds.contains(user.getProjectId())){
                delete(user.getId());
            }else {
                throw new DBException(ResultEnum.PROJECTIDID_NOT_EXIST);
            }
        }else {
            throw new DBException(ResultEnum.USER_NOT_EXIST);
        }
    }

    /**
     * 用户详情
     * @param id
     * @param projectIds
     */
    public User details(String id, Set<String> projectIds) {
        if (userRepository.exists(id)){
            User user=userRepository.findOne(id);
            if (projectIds.contains(user.getProjectId())){
                return user;
            }else {
                throw new DBException(ResultEnum.PROJECTIDID_NOT_EXIST);
            }
        }else {
            throw new DBException(ResultEnum.USER_NOT_EXIST);
        }
    }
}
