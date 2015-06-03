package com.dooioo.index.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 功能说明：HelloWordController
 * 作者：liuxing(2014-11-14 02:03)
 */
@Controller
@RequestMapping(value="/index")
public class IndexController {

    private static Log logger = LogFactory.getLog(IndexController.class);

    /**
     * 进入默认首页
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld() {
        logger.info("进入默认首页");
        return "index";
    }

}