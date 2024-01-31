package com.roy.langchainjavachat.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author roy
 */
@Slf4j
public class AppRunWrapper {

    public static ApplicationContext run(Class clazz, String[] args) {
        System.setProperty("appName", clazz.getSimpleName());
        ConfigurableApplicationContext application = SpringApplication.run(clazz, args);
        Environment env = application.getEnvironment();

        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}{}/doc.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name", ""),
                env.getProperty("server.port", ""),
                env.getProperty("server.servlet.context-path", ""));

        return application;
    }
}
