package com.dooioo.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 功能说明：HelloWordController
 * 作者：liuxing(2014-11-14 02:03)
 */
@Controller
@RequestMapping(value = "/test")
public class WebSocketController {

    /**
     * 进入websocket测试控制页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld() {
        return "websocket";
    }

}