package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.dto.PlanDTO;
import com.multi.dto.RoomDTO;
import com.multi.frame.MyService;
import com.multi.mapper.PlanMapper;

@Service
public class PlanService implements MyService<Integer, PlanDTO>{

	@Autowired
	PlanMapper mapper;

	@Override
	public void register(PlanDTO v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(PlanDTO v) throws Exception {
		mapper.update(v);
	}

	@Override
	public PlanDTO get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<PlanDTO> getall() throws Exception {
		return mapper.selectall();
	}

	public List<PlanDTO> plandetail(int accomid) throws Exception {
		return mapper.plandetail(accomid);
	}
	
	public List<PlanDTO> xy(int accomid) throws Exception {
		return mapper.xy(accomid);
	}
	
}
