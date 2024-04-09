package com.lekhraj.java.spring.SpringRest.configuration;

import com.lekhraj.java.spring.SpringRest.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<MyServlet> myServletRegistrationBean() {
        ServletRegistrationBean<MyServlet> registrationBean =new ServletRegistrationBean<>(new MyServlet(), "/custom");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }
}
