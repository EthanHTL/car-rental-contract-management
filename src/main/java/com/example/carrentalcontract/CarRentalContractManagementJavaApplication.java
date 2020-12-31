package com.example.carrentalcontract;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.example.carrentalcontract.mapper")
public class CarRentalContractManagementJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalContractManagementJavaApplication.class, args);
    }

}
