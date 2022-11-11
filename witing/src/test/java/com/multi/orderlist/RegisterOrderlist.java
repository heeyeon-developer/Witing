package com.multi.orderlist;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.OrderlistDTO;
import com.multi.dto.ReservationDTO;
import com.multi.service.OrderlistService;
import com.multi.service.ReservationService;

@SpringBootTest
class RegisterOrderlist {

	@Autowired
	OrderlistService service;
	
	@Test
	void contextLoads() {
		try {
			service.register(new OrderlistDTO(null,"hyk",2,200000,null,2, Date.valueOf("2022-11-08"),Date.valueOf("2022-11-10")));
			List<OrderlistDTO> list = service.getall();
			for(OrderlistDTO o : list)
				System.out.println(o);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
