package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.PlanDTO;
import com.multi.dto.RoomDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface PlanMapper extends MyMapper<Integer, PlanDTO> {
	public List<PlanDTO> plandetail(int accomid);
	public List<PlanDTO> xy(int accomid);

}
