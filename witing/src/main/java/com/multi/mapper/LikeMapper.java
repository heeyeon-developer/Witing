package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.LikeDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface LikeMapper extends MyMapper<Integer, LikeDTO> {
	public List<LikeDTO> likehotel(String custid) throws Exception;
}
