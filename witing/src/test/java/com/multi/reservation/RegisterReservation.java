package com.multi.reservation;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.ReservationDTO;
import com.multi.service.ReservationService;

@SpringBootTest
class RegisterReservation {

	@Autowired
	ReservationService service;
	
	@Test
	void contextLoads() {
		try {
			service.register(new ReservationDTO(null,4,"n_heeyeon3471",5, Date.valueOf("2022-11-08"),Date.valueOf("2022-11-10")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
