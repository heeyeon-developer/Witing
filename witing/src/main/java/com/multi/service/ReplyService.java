package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.dto.LikeDTO;
import com.multi.dto.ReplyDTO;
import com.multi.frame.MyService;
import com.multi.mapper.ReplyMapper;

@Service
public class ReplyService implements MyService<Integer, ReplyDTO>{

	@Autowired
	ReplyMapper mapper;

	@Override
	public void register(ReplyDTO v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(ReplyDTO v) throws Exception {
		mapper.update(v);
	}

	@Override
	public ReplyDTO get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<ReplyDTO> getall() throws Exception {
		return mapper.selectall();
	}

	public List<ReplyDTO> accomreply(int accomid) throws Exception{
		return mapper.accomreply(accomid);
	}
	
}
