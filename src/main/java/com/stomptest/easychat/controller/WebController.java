package com.stomptest.easychat.controller;

import com.stomptest.easychat.cache.UserCache;
import com.stomptest.easychat.model.pojo.User;
import com.stomptest.easychat.utils.UUIDUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: easychat
 * @Package: com.stomptest.easychat.controller
 * @ClassName: WebController
 * @Author: Oblivion
 * @Description:
 * @Date: 2020/1/8 18:18
 * @Version: 1.0
 */

@RestController
public class WebController {


    @PostMapping("login")
    public void login(HttpServletRequest request) {
        User user = new User();

    }

    @PostMapping("register")
    public void register(@RequestBody User user) {
        user.setUserId(UUIDUtils.create());
        System.out.println(user.toString());
        UserCache.addUser(UUIDUtils.create(), user);
    }
}
