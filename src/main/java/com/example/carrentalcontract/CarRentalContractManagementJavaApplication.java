package com.example.carrentalcontract;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.carrentalcontract.mapper"})
public class CarRentalContractManagementJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalContractManagementJavaApplication.class, args);
    }

}
