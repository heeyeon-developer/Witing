package com.multi.reply;





import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.CustDTO;
import com.multi.dto.ReplyDTO;
import com.multi.service.CustService;
import com.multi.service.ReplyService;

@SpringBootTest
class UpdateReply {

	@Autowired
	ReplyService service;
	
	@Test
	void contextLoads() {
		ReplyDTO reply = new ReplyDTO(54, 52, "dbb", "비밀댓글로 수정 부탁", null, 0, "y");
		try {
			service.modify(reply);
			List<ReplyDTO> list = service.getall();
			for(ReplyDTO r : list)
				System.out.println(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Update OK");
	}

}
