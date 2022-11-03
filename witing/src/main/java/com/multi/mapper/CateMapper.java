package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.CateDTO;
import com.multi.dto.RoomDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface CateMapper extends MyMapper<Integer, CateDTO> {
	public List<CateDTO> getcate();
}
