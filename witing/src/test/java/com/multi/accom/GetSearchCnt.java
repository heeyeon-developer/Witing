package com.multi.accom;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.AccompanyDTO;
import com.multi.dto.Criteria;
import com.multi.service.AccompanyService;

@SpringBootTest
class GetSearchCnt {

	@Autowired
	AccompanyService service;
	
	@Test
	void contextLoads() {
		
		try {
			Criteria cri = new Criteria();
			int cnt = Math.max(0,service.searchaccomcnt(cri, Date.valueOf("2022-11-17"), Date.valueOf("2022-12-31")));
			System.out.println(cnt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
