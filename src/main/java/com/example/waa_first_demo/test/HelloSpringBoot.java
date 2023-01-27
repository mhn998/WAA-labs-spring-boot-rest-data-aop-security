package com.example.waa_first_demo.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@RestController
//@RequestMapping("/")
@Configuration
public class HelloSpringBoot implements ApplicationContextAware {


    ApplicationContext applicationContext;
//    @RequestMapping
    public String printHello() {
        return "Welcome back Spring!";
    }

    public static void main(String[] args) {
        // this is created for demo purposes!
    }

    @Bean
    CommandLineRunner testAnyThingInTheFutureHere() {
        return (s) -> {
            System.out.println("applicationContext.getApplicationName() = " + applicationContext.getApplicationName());
            Arrays.stream(applicationContext.getBeanDefinitionNames()).map((name) -> new Tuple<>(name, applicationContext.getBean(name))).forEach(System.out::println);
        };
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Data
    @AllArgsConstructor
    private static class Tuple<V1,V2> {
        V1 v1;
        V2 v2;

    }

}
