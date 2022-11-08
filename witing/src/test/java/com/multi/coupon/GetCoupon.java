package com.multi.coupon;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.multi.dto.CouponDTO;
import com.multi.service.CouponService;

@SpringBootTest
class GetCoupon {

	@Autowired
	CouponService service;
	
	@Test
	void contextLoads() {
		List<CouponDTO> list = null;
		try {
			list = service.getcustcoupon("hyk");
			for(CouponDTO coupon : list)
				System.out.println(coupon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
	}

}
