package com.multi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.ReservationDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface ReservationMapper extends MyMapper<Integer, ReservationDTO> {

}
