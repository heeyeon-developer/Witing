package com.multi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.CityDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface CityMapper extends MyMapper<Integer, CityDTO> {
	
}
