package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.dto.LikeDTO;
import com.multi.dto.RoomDTO;
import com.multi.frame.MyService;
import com.multi.mapper.LikeMapper;

@Service
public class LikeService implements MyService<Integer, LikeDTO>{

	@Autowired
	LikeMapper mapper;

	@Override
	public void register(LikeDTO v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(LikeDTO v) throws Exception {
		mapper.update(v);
	}

	@Override
	public LikeDTO get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<LikeDTO> getall() throws Exception {
		return mapper.selectall();
	}
	
	public List<LikeDTO> likehotel(String custid) throws Exception {
		return mapper.likehotel(custid);
	}
	
}
