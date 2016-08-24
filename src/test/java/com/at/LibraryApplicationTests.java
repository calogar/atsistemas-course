package com.at;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.at.library.dto.BookDTO;

public class LibraryApplicationTests {

	@Test
	public void contextLoads() {
		// With RestTEmplate we can access every restful api
		final String url = "http://localhost/8080/7";
		final BookDTO book = (new RestTemplate()).getForObject(url, BookDTO.class);
		System.out.println(book);
		
		// If we wanted to test a create, we would use postForObject instead of create
		// BookDTO book = new RestTemplate().postForObject(url, book, BookDTO.class)
		
	}

}
