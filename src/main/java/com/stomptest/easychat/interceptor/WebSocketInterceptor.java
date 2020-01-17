package com.stomptest.easychat.interceptor;

import com.stomptest.easychat.model.pojo.User;
import com.stomptest.easychat.utils.UUIDUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: easychat
 * @Package: com.stomptest.easychat.interceptor
 * @ClassName: WebSocketInterceptor
 * @Author: Oblivion
 * @Description:
 * @Date: 2020/1/7 16:34
 * @Version: 1.0
 */
@Component
public class WebSocketInterceptor implements ChannelInterceptor {

    /**
     * 1、设置拦截器
     * 2、首次连接的时候，获取其Header信息，利用Header里面的信息进行权限认证
     * 3、通过认证的用户，使用 accessor.setUser(user); 方法，将登陆信息绑定在该 StompHeaderAccessor 上，在Controller方法上可以获取 StompHeaderAccessor 的相关信息
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        System.out.println(this.getClass().getCanonicalName() + " preSend");
        StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        //1、判断是否首次连接
        if (StompCommand.CONNECT.equals(stompHeaderAccessor.getCommand())) {

            User user = new User();
            user.setUserId(stompHeaderAccessor.getFirstNativeHeader("uid"));
            stompHeaderAccessor.setUser(user);
        }
        // 不是首次连接，已经登陆成功
        return message;
    }
}
