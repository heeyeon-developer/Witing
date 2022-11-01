package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.dto.CityDTO;
import com.multi.frame.MyService;
import com.multi.mapper.CityMapper;

@Service
public class CityService implements MyService<Integer, CityDTO>{

	@Autowired
	CityMapper mapper;

	@Override
	public void register(CityDTO v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(CityDTO v) throws Exception {
		mapper.update(v);
	}

	@Override
	public CityDTO get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<CityDTO> getall() throws Exception {
		return mapper.selectall();
	}
	
	
}
