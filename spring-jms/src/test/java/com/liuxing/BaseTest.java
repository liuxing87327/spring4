package com.liuxing;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * 单元测试基类
 *      SpringJUnit4ClassRunner 使用springtest主导单元测试
 *      ContextConfiguration 定位上下文配置文件
 *      TestExecutionListeners 测试执行监听器
 *      DependencyInjectionTestExecutionListener 监听测试类中的依赖注入是否正确
 *      TransactionalTestExecutionListener 监听测试类中的事务
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( "classpath:spring/applicationContext.xml" )
@TestExecutionListeners(listeners={
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public abstract class BaseTest {

}

