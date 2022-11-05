package com.example.LabReservationProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})		//테스트실행 위해 (exclude={DataSourceAutoConfiguration.class})추가 하였음, 없으면 DB연결 안됬다고 에러남, DB연결시 지워야함
public class LabReservationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabReservationProjectApplication.class, args);
	}
}
