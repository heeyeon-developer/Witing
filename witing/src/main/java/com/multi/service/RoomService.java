package com.multi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.dto.HotelDTO;
import com.multi.dto.RoomDTO;
import com.multi.frame.MyService;
import com.multi.mapper.RoomMapper;

@Service
public class RoomService implements MyService<Integer, RoomDTO>{

	@Autowired
	RoomMapper mapper;

	@Override
	public void register(RoomDTO v) throws Exception {
		mapper.insert(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		mapper.delete(k);
	}

	@Override
	public void modify(RoomDTO v) throws Exception {
		mapper.update(v);
	}

	@Override
	public RoomDTO get(Integer k) throws Exception {
		return mapper.select(k);
	}

	@Override
	public List<RoomDTO> getall() throws Exception {
		return mapper.selectall();
	}

	public List<RoomDTO> roomall(int hotelid) throws Exception {
		return mapper.roomall(hotelid);
	}

	public List<RoomDTO> getroom(int roomid) throws Exception {
		return mapper.getroom(roomid);
	}
	
}
