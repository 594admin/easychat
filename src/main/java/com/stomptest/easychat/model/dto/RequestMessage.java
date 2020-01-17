package com.stomptest.easychat.model.dto;

import lombok.Data;

/**
 * @ProjectName: easychat
 * @Package: com.stomptest.easychat.pojo
 * @ClassName: RequestMessage
 * @Author: Oblivion
 * @Description: 浏览器向服务端请求的消息
 * @Date: 2020/1/7 16:19
 * @Version: 1.0
 */
@Data
public class RequestMessage {

    private String content;

    // 接收人
    private String receiver;
}
