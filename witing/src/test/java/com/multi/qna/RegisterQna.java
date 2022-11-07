package com.multi.qna;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.PostDTO;
import com.multi.service.PostService;

@SpringBootTest
class RegisterQna {

	@Autowired
	PostService service;
	
	@Test
	void contextLoads() {
		PostDTO post = new PostDTO(0, "kmj", null, 1, "test", "test", null, null, "quest", 0);
		try {

			service.register(post);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}