package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.dto.CouponDTO;
import com.multi.dto.Criteria;
import com.multi.frame.MyService;
import com.multi.mapper.CouponMapper;

@Service
public class CouponService implements MyService<String, CouponDTO>{
	@Autowired
	CouponMapper mapper;

	@Override
	public void register(CouponDTO v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(String k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(CouponDTO v) throws Exception {
		mapper.update(v);
	}

	@Override
	public CouponDTO get(String k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<CouponDTO> getall() throws Exception {
		return mapper.selectall();
	}
	
	public List<CouponDTO> getcustcoupon(String custid) throws Exception{
		return mapper.getcustcoupon(custid);
	}
	
	public List<CouponDTO> couponpage(Criteria cri) throws Exception {
		return mapper.couponpage(cri);
	}
	public int couponcnt(Criteria cri) throws Exception {
		return mapper.couponcnt(cri);
	}

}
