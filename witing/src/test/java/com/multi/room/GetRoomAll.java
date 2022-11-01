package com.multi.room;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.RoomDTO;
import com.multi.service.RoomService;

@SpringBootTest
class GetRoomAll {

	@Autowired
	RoomService service;
	
	@Test
	void contextLoads() {
		List<RoomDTO> list = null;
		try {
			list = service.getroom(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(RoomDTO r:list) {
			System.out.println(r);
		}
	}

}




