package com.multi.like;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.LikeDTO;
import com.multi.service.LikeService;

@SpringBootTest
class GetLike {

	@Autowired
	LikeService service;
	
	@Test
	void contextLoads() {
		LikeDTO like = null;
		try {
			like = service.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(like);
	
	}

}
