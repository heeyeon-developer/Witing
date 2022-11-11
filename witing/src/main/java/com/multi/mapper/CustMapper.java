package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.CustDTO;
import com.multi.frame.MyMapper;

@Mapper
@Repository
public interface CustMapper extends MyMapper<String,CustDTO>{
	public List<CustDTO> custget(String custid) throws Exception;
}
