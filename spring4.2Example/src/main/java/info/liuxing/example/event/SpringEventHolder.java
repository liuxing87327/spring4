package info.liuxing.example.event;

import info.liuxing.example.context.SpringContextHolder;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;

/**
 * Spring事件发布工具
 *
 * @author ：liuxing
 * @since ：2015-09-30 23:47
 */
public class SpringEventHolder {

    /**
     * 发布事件
     * @param event
     */
    public static void publishEvent(ApplicationEvent event) {
        Assert.notNull(event, "无效的事件");
        SpringContextHolder.getApplicationContext().publishEvent(event);
    }

}
