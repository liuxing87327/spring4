package info.liuxing.example.controller;

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
