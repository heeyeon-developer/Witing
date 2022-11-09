package com.multi.reservation;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.ReservationDTO;
import com.multi.service.ReservationService;

@SpringBootTest
class GetCustReserv {

	@Autowired
	ReservationService service;
	
	@Test
	void contextLoads() {
		List<ReservationDTO> list = null;
		try {
			list = service.custreserv("hyk");
			for(ReservationDTO coupon : list)
				System.out.println(coupon);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
