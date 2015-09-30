package info.liuxing.example.context;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 
 * 类功能说明：以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext。
 * 必须在配置中注入或注解（@Service）注入，注意要关闭延时，lazy-init="false"或@Lazy(false)
 * Title: SpringContextHolder.java
 * @author 刘兴
 * @since  2014-1-17 上午1:42:14
 * @version V1.0
 */
@Lazy(false)
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;
    
    /**
     * 
     * 功能说明：取得存储在静态变量中的ApplicationContext.
     * @author 刘兴 
     * @Date 2014-1-17 上午1:42:34
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 
     * 功能说明：从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @author 刘兴 
     * @Date 2014-1-17 上午1:42:58
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 
     * 功能说明：从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @author 刘兴 
     * @Date 2014-1-17 上午1:43:12
     * @param requiredType
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 
     * 功能说明：清除SpringContextHolder中的ApplicationContext为Null.
     * @author 刘兴 
     * @Date 2014-1-17 上午1:43:22
     */
    public static void clearHolder() {
//        LOGGER.debug("清除SpringContextHolder中的ApplicationContext：" + applicationContext);
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     * (non-Javadoc)   
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
//        LOGGER.info("注入ApplicationContext到SpringContextHolder");

        if (SpringContextHolder.applicationContext != null) {
//            LOGGER.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
        }

        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     * (non-Javadoc)
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    /**
     * 
     * 功能说明：检查ApplicationContext不为空.
     * @author 刘兴 
     * @Date 2014-1-17 上午1:44:09
     */
    private static void assertContextInjected() {
        Assert.notNull(applicationContext, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
    }
}