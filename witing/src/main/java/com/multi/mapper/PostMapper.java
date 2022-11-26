package com.multi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.dto.AccompanyDTO;
import com.multi.dto.Criteria;
import com.multi.dto.PostDTO;
import com.multi.frame.MyMapper;

@Repository
@Mapper
public interface PostMapper extends MyMapper<Integer, PostDTO> {
	public List<PostDTO> myqna(String custid) throws Exception;
	public List<PostDTO> qnalist5(int hotelid) throws Exception;
	public List<PostDTO> hotelqnaall(int hotelid) throws Exception;
	public void qnainsert(PostDTO post) throws Exception;
	public void reviewinsert(PostDTO post) throws Exception;
	public PostDTO selectanswer(int postid) throws Exception;
	public PostDTO answercheck(int toppostid) throws Exception;
	public List<PostDTO> myreview(String custid) throws Exception;
	public PostDTO reviewdetail(int postid) throws Exception;
	public List<PostDTO> reviewlist5(int hotelid) throws Exception;
	public List<PostDTO> hotelreviewall(int hotelid) throws Exception;
	public PostDTO reviewocr(int orderid) throws Exception;
	public List<PostDTO> myreviewpage(Criteria cri) throws Exception;
	public int myreviewcnt(Criteria cri) throws Exception;
	public List<PostDTO> myqnapage(Criteria cri) throws Exception;
	public int myqnacnt(Criteria cri) throws Exception;
}
