package com.rdipple.javazoosassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc //usually don't do this, this is an except situation
@EnableJpaAuditing
@SpringBootApplication
public class
JavazoosApplication {

    public static void main(String[] args) {

       ApplicationContext ctx = SpringApplication.run(JavazoosApplication.class, args);

        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
