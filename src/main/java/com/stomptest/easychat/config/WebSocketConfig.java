package com.stomptest.easychat.config;

import com.stomptest.easychat.constant.StompConstant;
import com.stomptest.easychat.interceptor.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @EnableWebSocketMessageBroker 注解开启使用STOMP协议来传输基于MessageBroker代理的消息
 * @ProjectName: easychat
 * @Package: com.stomptest.easychat.config
 * @ClassName: WebSocketConfig
 * @Author: Oblivion
 * @Description:
 * @Date: 2020/1/7 16:24
 * @Version: 1.0
 */


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private WebSocketInterceptor webSocketInterceptor;

    /**
     * 配置消息代理
     * 如果不重载它的话，将会自动配置一个简单的内存消息代理，用它来处理以"/topic"为前缀的消息
     *
     * STOMP 的消息根据前缀的不同分为三种。
     * 如下，以 /app 开头的消息都会被路由到带有@MessageMapping 或 @SubscribeMapping 注解的方法中；
     * 以/topic 或 /queue 开头的消息都会发送到STOMP代理中，根据你所选择的STOMP代理不同，目的地的可选前缀也会有所限制；
     * 以/user开头的消息会将消息重路由到某个用户独有的目的地上。
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 广播式使用/topic，点对点式使用/user
        // 这句话表示在topic和user这两个域上可以向客户端发消息
        registry.enableSimpleBroker(StompConstant.STOMP_TOPIC, StompConstant.STOMP_USER);
        // 这句话表示给指定用户发送一对一的主题前缀是"/user"
        registry.setUserDestinationPrefix(StompConstant.STOMP_USER);
        // 这句话表示客户单向服务器端发送时的主题上面需要加"/app"作为前缀
        registry.setApplicationDestinationPrefixes(StompConstant.STOMP_APP);
    }

    /**
     * 注册STOMP的节点，并映射指定的url
     * 将 StompConstant.STOMP_ENDPOINT 注册为一个 STOMP 端点。这个路径与之前发送和接收消息的目的地路径有所
     * 不同。这是一个端点，客户端在订阅或发布消息到目的地路径前，要连接到该端点。
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 注册 Stomp的端点
         * addEndpoint：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址
         * withSockJS：指定端点使用SockJS协议
         */
        registry.addEndpoint(StompConstant.STOMP_ENDPOINT)
//                .setAllowedOrigins("*") // 添加允许跨域访问
                .withSockJS();
    }

    /**
     * 注册消息拦截器
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketInterceptor);
    }

}
