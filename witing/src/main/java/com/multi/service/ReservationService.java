package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.dto.ReservationDTO;
import com.multi.frame.MyService;
import com.multi.mapper.ReservationMapper;

@Service
public class ReservationService implements MyService<Integer, ReservationDTO> {

	@Autowired
	ReservationMapper mapper;
	@Override
	public void register(ReservationDTO v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(ReservationDTO v) throws Exception {
		mapper.update(v);
	}

	@Override
	public ReservationDTO get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<ReservationDTO> getall() throws Exception {
		return mapper.selectall();
	}
	
	public List<ReservationDTO> custreserv(String k) throws Exception {
		return mapper.custreserv(k);
	}

}
