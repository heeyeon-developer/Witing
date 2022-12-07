package com.multi.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.Criteria;
import com.multi.dto.CriteriaHotel;
import com.multi.dto.HotelDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface HotelMapper extends MyMapper<Integer, HotelDTO> {
	public List<HotelDTO> hotelcity() throws Exception;
	public List<HotelDTO> cityshotel(Integer cityid) throws Exception;
	public List<HotelDTO> searchhotel(Integer cityid, Integer cateid, Date sdate, Date edate) throws Exception;
	
	public List<HotelDTO> hotelpage(CriteriaHotel crihotel) throws Exception;
	public int hotelcnt(CriteriaHotel crihotel) throws Exception;

	public int cityshotelcnt(Integer cityid, CriteriaHotel crihotel) throws Exception;
}
