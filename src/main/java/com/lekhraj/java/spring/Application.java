package com.lekhraj.java.spring;

import com.lekhraj.java.spring.SpringProperties.bean.Prop2Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

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
		app.run(args);

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
OS Environment Variables way-4
application.properties in Current Directory on server
application.properties in the classpath (src/main/resources or the packaged jar file)

 */

