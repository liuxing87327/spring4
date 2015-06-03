package com.dooioo.websocket.config;

import com.dooioo.websocket.handler.WebSocketHandler;
import com.dooioo.websocket.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * 功能说明：WebSocket配置，使用bean配置的方式
 * 作者：liuxing(2015-01-25 03:41)
 */
//@Configuration
//@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(telWebSocketHandler(), "/websocket").addInterceptors(new WebSocketHandshakeInterceptor());
    }

    @Bean
    public WebSocketHandler telWebSocketHandler() {
        return new WebSocketHandler();
    }

    /**
     * 底层websocket连接配置
     * 这个是针对Tomcat, WildFly, Glassfish；
     * jetty的方法有差异，需要提供一个配置DefaultHandshakeHandler的方法，详见官网示例文档
     * 详见spring-websocket文档#21.2.5
     * @return
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        container.setAsyncSendTimeout(5000);
        container.setMaxSessionIdleTimeout(1000 * 60 * 30);
        return container;
    }

}