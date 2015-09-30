#Spring 4.2新特性

http://spring.io/blog/2015/07/31/spring-framework-4-2-goes-ga

##@Bean支持java8接口的默认方法
Spring 4.2以前，以下代码的@Bean是不会生效

```java
public interface DemoServiceConfig {

    @Bean
    default Person Person() {
        System.out.println("找到一个默认方法初始的bean");
        return new Person();
    }

}
```

```java
@Configuration
public class AppConfig implements DemoServiceConfig {

}
```

输出结果
```bash
找到一个默认方法初始的bean
```

##基于注解的事件回调（@EventListener）
一个人员创建后通知的示例
1.创建扩展了PersonEvent ApplicationEvent类
2.创建PersonService实现ApplicationEventPublisherAware接口，
    必须把该类注入到spring容器，容器可以识别bean作为事件发布者，因为它实现了ApplicationEventPublisherAware接口
3.创建PersonNotification，使用@EventListener注解要处理通知的方法。

PersonEvent

```java
import org.springframework.context.ApplicationEvent;

/**
 * PersonEvent
 *
 * @author ：liuxing
 * @since ：2015-08-09 17:40
 */
public class PersonEvent extends ApplicationEvent {

    private static final long serialVersionUID = -6956330900053582201L;

    private String name;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PersonEvent(Object source, String name) {
        super(source);
        this.name = name;
        System.out.println("Person事件创建好了!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

PersonService

```java
import info.liuxing.example.event.PersonEvent;
import info.liuxing.example.model.Person;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * PersonService
 *
 * @author ：liuxing
 * @since ：2015-08-09 17:38
 */
@Service
public class PersonService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public Person getPerson(String name, int age, String sex) {
        Person person = new Person(name, age, sex);
        System.out.println("Person创建好了");
        PersonEvent event = new PersonEvent(this, name);
        System.out.println("发布Person事件");
        publisher.publishEvent(event);
        return person;
    }

}
```

PersonNotification

```java
import info.liuxing.example.event.PersonEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * PersonNotification
 *
 * @author ：liuxing
 * @since ：2015-08-09 17:46
 */
@Component
public class PersonNotification {

    private int notificationId;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @EventListener
    public void processPersonEvent(PersonEvent event) {
        System.out.println("收到Person事件：" + event.getName());
    }
}
```

调用一下

```java
import info.liuxing.example.model.Person;
import info.liuxing.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 *
 * @author ：liuxing
 * @since ：2015-08-09 16:52
 */
@RestController
@RequestMapping(value = "/")
public class DemoController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/user")
    public Person test() {
        return personService.getPerson("张三", 25, "男");
    }

}
```

输出结果：
```bash
Person创建好了
Person事件创建好了!
发布Person事件
收到Person事件：张三
```

https://spring.io/blog/2015/02/11/better-application-events-in-spring-framework-4-2
