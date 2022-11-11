package com.multi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.PayDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface PayMapper extends MyMapper<Integer, PayDTO> {
}
