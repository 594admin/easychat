package com.stomptest.easychat.model.pojo;

import lombok.Data;

import java.io.Serializable;
import java.security.Principal;

/**
 * @ProjectName: easychat
 * @Package: com.stomptest.easychat.pojo
 * @ClassName: User
 * @Author: Oblivion
 * @Description:
 * @Date: 2020/1/7 18:43
 * @Version: 1.0
 */
@Data
public class User implements Principal, Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 地址
     */
    private String address;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户状态
     */
    private int status;

    @Override
    public String getName() {
        return this.userId;
    }

}
