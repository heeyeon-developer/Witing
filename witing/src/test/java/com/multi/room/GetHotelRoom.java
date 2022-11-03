package com.multi.room;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.RoomDTO;
import com.multi.service.RoomService;

@SpringBootTest
class GetHotelRoom {

	@Autowired
	RoomService service;
	
	@Test
	void contextLoads() {
		List<RoomDTO> list = null;
		try {
			service.roomall(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(RoomDTO r:list) {
			System.out.println(r);
		}
	}

}




