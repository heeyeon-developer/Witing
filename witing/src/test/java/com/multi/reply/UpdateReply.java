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
		ReplyDTO reply = new ReplyDTO(3, 3, "k_bdb97", "저도 가고싶어용가리", null, 0, null);
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
