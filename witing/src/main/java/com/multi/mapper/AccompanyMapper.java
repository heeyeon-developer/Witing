package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.AccompanyDTO;
//import com.multi.dto.Criteria;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface AccompanyMapper extends MyMapper<Integer, AccompanyDTO> {
	public void accomdel(int accomid) throws Exception;
//	public List<AccompanyDTO> selectpaging(Criteria criteria);
}
