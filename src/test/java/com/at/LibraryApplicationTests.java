package com.at;

import org.junit.Test;

import com.at.library.dto.BookDTO;

public class LibraryApplicationTests {

	@Test
	public void contextLoads() {
		// With RestTEmplate we can access every restful api
		final String url = "http://localhost/8080/7";
		final BookDTO book = (new RestTemplate).getForObject(url, BookDTO.class);
		System.out.println(book);
	}

}
