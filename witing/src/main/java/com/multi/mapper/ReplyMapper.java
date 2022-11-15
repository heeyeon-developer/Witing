package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.multi.dto.ReplyDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface ReplyMapper extends MyMapper<Integer, ReplyDTO> {
	public List<ReplyDTO> accomreply(int accomid) throws Exception;
}
