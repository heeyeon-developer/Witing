package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.RoomDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface RoomMapper extends MyMapper<Integer, RoomDTO> {
	public List<RoomDTO> roomall(int hotelid);
	public List<RoomDTO> getroom(int roomid);
}
