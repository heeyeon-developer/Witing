package com.multi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.AccompanyDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface AccompanyMapper extends MyMapper<Integer, AccompanyDTO> {
	
}
