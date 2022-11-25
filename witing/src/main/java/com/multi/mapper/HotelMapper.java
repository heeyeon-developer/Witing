package com.multi.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.HotelDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface HotelMapper extends MyMapper<Integer, HotelDTO> {
	public List<HotelDTO> hotelcity() throws Exception;
	public List<HotelDTO> cityshotel(Integer cityid) throws Exception;
	public List<HotelDTO> searchhotel(Integer cityid, Integer cateid, Date sdate, Date edate) throws Exception;
}
