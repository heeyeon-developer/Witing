package com.multi.reply;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.ReplyDTO;
import com.multi.service.AccompanyService;
import com.multi.service.PlanService;
import com.multi.service.ReplyService;

@SpringBootTest
class DeleteReply {
	
	@Autowired
	ReplyService service;

	@Test
	void contextLoads() {
		try {
			service.remove(9);
			List<ReplyDTO> list = service.getall();
			for(ReplyDTO r : list) {
				System.out.println(r);
			}
			System.out.println("DELETED");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
