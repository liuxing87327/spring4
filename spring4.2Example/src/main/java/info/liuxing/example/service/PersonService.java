package info.liuxing.example.service;

import info.liuxing.example.event.PersonEvent;
import info.liuxing.example.event.SpringEventHolder;
import info.liuxing.example.model.Person;
import org.springframework.stereotype.Service;

/**
 * PersonService
 *
 * @author ：liuxing
 * @since ：2015-08-09 17:38
 */
@Service
public class PersonService {

    public Person getPerson(String name, int age, String sex) {
        Person person = new Person(name, age, sex);
        System.out.println("Person创建好了");
        PersonEvent event = new PersonEvent(this, name);
        System.out.println("发布Person事件");
        SpringEventHolder.publishEvent(event);
        return person;
    }

}
