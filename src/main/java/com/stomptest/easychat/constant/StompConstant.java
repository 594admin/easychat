package com.stomptest.easychat.constant;

/**
 * @ProjectName: easychat
 * @Package: com.stomptest.easychat.constant
 * @ClassName: StompConstant
 * @Author: Oblivion
 * @Description: stomp相关常量
 * @Date: 2020/1/7 16:28
 * @Version: 1.0
 */
public interface StompConstant {

    /**
     * STOMP的节点
     */
    String STOMP_ENDPOINT = "/easychat";
    /**
     * 广播式
     */
    String STOMP_TOPIC = "/topic";
    /**
     * 一对一式
     */
    String STOMP_USER = "/user";
    /**
     * 客户单向服务器端发送
     */
    String STOMP_APP = "/app";
    /**
     * 单用户消息订阅地址
     */
    String SUB_USER = "/chat";
    /**
     * 单用户消息发布地址
     */
    String PUB_USER = "/chat";
    /**
     * 聊天室消息发布地址
     */
    String PUB_CHAT_ROOM = "/chatRoom";

    /**
     * 聊天室消息订阅地址
     */
    String SUB_CHAT_ROOM = "/topic/chatRoom";
    /**
     * 异常消息订阅地址
     */
    String SUB_ERROR = "/error";
    /**
     * 用户上下线状态消息订阅地址
     */
    String SUB_STATUS = "/topic/status";
    /**
     * 聊天室消息撤消
     */
    String PUB_CHAT_ROOM_REVOKE = "/chatRoom/revoke";
}
