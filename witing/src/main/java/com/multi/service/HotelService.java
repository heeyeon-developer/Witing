package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.dto.HotelDTO;
import com.multi.frame.MyService;
import com.multi.mapper.HotelMapper;

@Service
public class HotelService implements MyService<Integer, HotelDTO>{

	@Autowired
	HotelMapper mapper;

	@Override
	public void register(HotelDTO v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(HotelDTO v) throws Exception {
		mapper.update(v);
	}

	@Override
	public HotelDTO get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<HotelDTO> getall() throws Exception {
		return mapper.selectall();
	}
	
	public List<HotelDTO> cityname(int cityid) throws Exception {
		return mapper.cityname(cityid);
	}
	
}
