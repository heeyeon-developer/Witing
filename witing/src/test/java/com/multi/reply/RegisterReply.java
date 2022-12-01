package com.multi.reply;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.OrderlistDTO;
import com.multi.dto.PayDTO;
import com.multi.dto.ReplyDTO;
import com.multi.dto.ReservationDTO;
import com.multi.service.OrderlistService;
import com.multi.service.PayService;
import com.multi.service.ReplyService;
import com.multi.service.ReservationService;

@SpringBootTest
class RegisterReply {

	@Autowired
	ReplyService service;
	
	@Test
	void contextLoads() {
		try {
			service.register(new ReplyDTO(0, 3, "k_bdb97", "저도 가고싶어요~", null, 0, "n"));
			List<ReplyDTO> list = service.getall();
			for(ReplyDTO r : list)
				System.out.println(r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
