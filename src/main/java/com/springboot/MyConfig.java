package com.springboot;

import org.springframework.context.annotation.Bean;

public class MyConfig {

	@Bean
	public User user() {
		return new User();
	}
	
	@Bean
	public Person person() {
		return new Person();
	}
}
