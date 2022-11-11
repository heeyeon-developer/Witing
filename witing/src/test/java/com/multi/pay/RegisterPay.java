package com.multi.pay;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.OrderlistDTO;
import com.multi.dto.PayDTO;
import com.multi.dto.ReservationDTO;
import com.multi.service.OrderlistService;
import com.multi.service.PayService;
import com.multi.service.ReservationService;

@SpringBootTest
class RegisterPay {

	@Autowired
	PayService service;
	
	@Test
	void contextLoads() {
		try {
			service.register(new PayDTO(null,2,300000,"결제완료","카드결제"));
			List<PayDTO> list = service.getall();
			for(PayDTO o : list)
				System.out.println(o);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
