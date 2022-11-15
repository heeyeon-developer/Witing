package com.multi.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.ReplyDTO;
import com.multi.service.ReplyService;

@SpringBootTest
class GetReply {

	@Autowired
	ReplyService service;
	
	@Test
	void contextLoads() {
		ReplyDTO reply = null;
		try {
			reply = service.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(reply);
	
	}

}
