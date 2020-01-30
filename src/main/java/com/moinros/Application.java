package com.moinros;

import com.moinros.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Config.init();
        SpringApplication.run(Application.class, args);
    }

}
