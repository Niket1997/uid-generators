package org.niket;

import org.niket.db.Migration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        Migration.reset();
        SpringApplication.run(Main.class, args);
    }
}