package hu.kalee.multi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;

import hu.kalee.multitenant.EnableMultiTenancy;

@SpringBootApplication
@EnableMultiTenancy
public class MultiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiApplication.class, args);
    }

}
