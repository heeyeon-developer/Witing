package com.multi.room;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.RoomDTO;
import com.multi.service.RoomService;

@SpringBootTest
class GetRoom {

	@Autowired
	RoomService service;
	
	@Test
	void contextLoads() {
		RoomDTO room = null;
		try {
			room = service.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(room);
	
	}

}
