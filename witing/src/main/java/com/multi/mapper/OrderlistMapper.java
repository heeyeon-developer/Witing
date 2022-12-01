package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.Criteria;
import com.multi.dto.OrderlistDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface OrderlistMapper extends MyMapper<Integer, OrderlistDTO> {
	public List<OrderlistDTO> custorder(String custid) throws Exception;
	public List<OrderlistDTO> custorderpage(Criteria cri) throws Exception;
	public int custordercnt(Criteria cri) throws Exception;
}
