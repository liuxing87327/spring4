package info.liuxing.example.event;

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
