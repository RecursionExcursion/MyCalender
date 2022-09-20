package com.foofincworks.MyCalender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
public class MyCalenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCalenderApplication.class, args);
	}

}
