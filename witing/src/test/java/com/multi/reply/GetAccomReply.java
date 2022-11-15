package com.multi.reply;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.ReplyDTO;
import com.multi.service.ReplyService;

@SpringBootTest
class GetAccomReply {

	@Autowired
	ReplyService service;
	
	
	@Test
	void contextLoads() {
		List<ReplyDTO> list = null;
		try {
			list = service.accomreply(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(ReplyDTO r:list) {
			System.out.println(r);
		}
		
	}

}




