package com.stomptest.easychat.controller;

import com.stomptest.easychat.model.dto.RequestMessage;
import com.stomptest.easychat.model.dto.ResponseMessage;
import com.stomptest.easychat.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: easychat
 * @Package: com.stomptest.easychat.controller
 * @ClassName: WebSocketController
 * @Author: Oblivion
 * @Description:
 * @Date: 2020/1/7 16:20
 * @Version: 1.0
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * @param requestMessage
     * @return
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo默认 消息将被发送到与传入消息相同的目的地
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换
     */
    @MessageMapping("/receive")
    @SendTo("/topic/getResponse")
    public ResponseMessage receive(RequestMessage requestMessage) {
        System.out.println(requestMessage);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage(requestMessage.getContent());
//        responseMessage.setResponseMessage("WebSocketController receive [" + count.incrementAndGet() + "] records");
        return responseMessage;
    }

    @MessageMapping("/pmsg")
//    @SendToUser("/pmsg")
    public void privateMsg(User user, RequestMessage requestMessage) {
        System.out.println(requestMessage);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage(requestMessage.getContent());
        messagingTemplate.convertAndSendToUser(requestMessage.getReceiver(), "pmsg", responseMessage);
        messagingTemplate.convertAndSendToUser(user.getUserId(), "pmsg", responseMessage);
    }

}
