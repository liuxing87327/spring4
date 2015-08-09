package info.liuxing.example.config;

import info.liuxing.example.model.Person;
import org.springframework.context.annotation.Bean;

/**
 * DemoServiceConfig
 *
 * @author ：liuxing
 * @since ：2015-08-09 17:18
 */
public interface DemoServiceConfig {

    @Bean
    default Person Person() {
        System.out.println("找到一个默认方法初始的bean");
        return new Person();
    }

}
