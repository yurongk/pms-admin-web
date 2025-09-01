package com.yurongku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.yurongku")
@EntityScan("com.yurongku.entity")
@EnableJpaRepositories("com.yurongku.repository")
@MapperScan("com.yurongku.mapper")
@ConfigurationPropertiesScan(basePackages = "com.yurongku.config")
public class PmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmsAdminApplication.class, args);
    }
}