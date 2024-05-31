package com.lekhraj.java.spring;

import com.lekhraj.java.spring.Spring_03_Properties.bean.Prop2Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
@EnableConfigurationProperties({Prop2Map.class})

public class Application {
	public static void main(String[] args)
	{
		//SpringApplication.run(Application.class, args);

		System.setProperty("server.servlet.context-path", "/spring"); //way-3
		System.setProperty("server.error.whitelabel.enabled=", "false"); //way-3

		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8083")); //way-1
		//app.setDefaultProperties(Collections.singletonMap("spring.config.additional-location", "file:/application.properties"));
		app.run(args);
	}

	// Another Nice way to write CommandLineRunner (Functional interface)
	@Bean
	public CommandLineRunner runnerBean1() throws Exception {
		return (String[] args) -> { System.out.println(" --- runnerBean1 --- ");};
	}
}
/*
way-4
set SERVER_SERVLET_CONTEXT_PATH=/spring
set SERVER_PORT=8083

way-2
java -jar app.jar --server.servlet.context-path=/spring

-- Priority Order of Configurations --
Java Config way-1
Command Line Arguments way-2
Java System Properties way-3
Env Variables way-4
application.properties (ext) in Current Directory on server
application.properties (int) in the classpath (src/main/resources or the packaged jar file)
 */

