package com.example.carrentalcontract;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.example.carrentalcontract.mapper")
@PropertySource("classpath:application.yml")
public class CarRentalContractManagementJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalContractManagementJavaApplication.class, args);
    }

}
