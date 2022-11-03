package com.multi.like;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.LikeDTO;
import com.multi.service.LikeService;

@SpringBootTest
class GetAllLike {

	@Autowired
	LikeService service;
	
	
	@Test
	void contextLoads() {
		List<LikeDTO> list = null;
		try {
			list = service.getall();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(LikeDTO l:list) {
			System.out.println(l);
		}
		
	}

}




