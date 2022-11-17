package com.example.LabReservationProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(/*exclude={DataSourceAutoConfiguration.class}*/)		//테스트실행 위해 (exclude={DataSourceAutoConfiguration.class})추가 하였음, 없으면 DB연결 안됬다고 에러남, DB연결시 지워야함
@EnableScheduling //타임 트리거 기능을 위한 스케줄 어노테이션 선언
public class LabReservationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabReservationProjectApplication.class, args);
	}
}
