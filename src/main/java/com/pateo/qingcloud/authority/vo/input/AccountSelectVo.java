package com.pateo.qingcloud.authority.vo.input;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author seangogo
 */
@Getter
@Setter
public class AccountSelectVo {
    /**
     * 用户名condition
     */
    private String userName;

    /**
     * 创建时间开始
     */
    private Date createdDateStart;

    /**
     * 创建时间结束
     */
    private Date createdDateEnd;

}
