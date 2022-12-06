package com.multi.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.AccompanyDTO;
import com.multi.dto.Criteria;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface AccompanyMapper extends MyMapper<Integer, AccompanyDTO> {
	public void accomdel(int accomid) throws Exception;
	
	public List<AccompanyDTO> accompage(Criteria cri) throws Exception;
	public int accomcnt(Criteria cri) throws Exception;
	public List<AccompanyDTO> searchaccom(Criteria cri, Date sdate, Date edate) throws Exception;
	public int searchaccomcnt(Criteria cri, Date sdate, Date edate) throws Exception;

}
