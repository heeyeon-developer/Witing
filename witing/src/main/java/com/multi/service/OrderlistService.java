package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.dto.Criteria;
import com.multi.dto.OrderlistDTO;
import com.multi.dto.ReservationDTO;
import com.multi.frame.MyService;
import com.multi.mapper.OrderlistMapper;
import com.multi.mapper.ReservationMapper;

@Service
public class OrderlistService implements MyService<Integer, OrderlistDTO> {

	@Autowired
	OrderlistMapper mapper;
	@Override
	public void register(OrderlistDTO v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(OrderlistDTO v) throws Exception {
		mapper.update(v);
	}

	@Override
	public OrderlistDTO get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<OrderlistDTO> getall() throws Exception {
		return mapper.selectall();
	}
	
	public List<OrderlistDTO> custorder(String k) throws Exception {
		return mapper.custorder(k);
	}
	public List<OrderlistDTO> custorderpage(Criteria cri) throws Exception {
		return mapper.custorderpage(cri);
	}
	public int custordercnt(Criteria cri) throws Exception {
		return mapper.custordercnt(cri);
	}

}
