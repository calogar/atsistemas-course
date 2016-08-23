package com.at.library;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

	// We write it this way so we can change the DozerBeanMapper initialization without changing all the code
	
	@Bean
	public DozerBeanMapper dozer() {
		return new DozerBeanMapper();
	}

}