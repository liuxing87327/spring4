package info.liuxing.example.event.listener;

import info.liuxing.example.event.PersonEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * PersonNotification
 *
 * @author ：liuxing
 * @since ：2015-08-09 17:46
 */
@Component
public class PersonEventListener {

    private int notificationId;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @EventListener(classes = {ContextStartedEvent.class})
    public void processPersonEvent(PersonEvent event) {
        System.out.println("收到Person事件：" + event.getName());
    }
}
