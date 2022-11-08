package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.CouponDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface CouponMapper extends MyMapper<String, CouponDTO> {
	public List<CouponDTO> getcustcoupon(String custid) throws Exception;
}
