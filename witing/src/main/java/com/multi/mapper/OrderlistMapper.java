package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.OrderlistDTO;
import com.multi.dto.ReservationDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface OrderlistMapper extends MyMapper<Integer, OrderlistDTO> {
	public List<OrderlistDTO> custorder(String custid) throws Exception;
}
