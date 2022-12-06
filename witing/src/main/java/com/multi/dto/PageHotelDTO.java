package com.multi.dto;

import lombok.Data;

@Data

public class PageHotelDTO {
	private int pageStart;			// 페이지 시작 번호
    private int pageEnd;			// 페이지 끝 번호
    private boolean next, prev;		// 이전, 다음 버튼 존재 유무
    private int total;				// 행 전체 개수
    
    private CriteriaHotel crihotel;			// 페이지당 게시글 행 수

	public PageHotelDTO(int total, CriteriaHotel crihotel) {
		this.total = total;
		this.crihotel = crihotel;
		
		// 페이지 끝번호
		this.pageEnd = (int)(Math.ceil(crihotel.getPageNum()/10.0)) * 10;
		
		// 페이지 시작번호
		this.pageStart = this.pageEnd - 9;
		
		// 전체 마지막 페이지 번호
		int realEnd = (int)(Math.ceil((total*1.0) / crihotel.getAmount()));
		
		if(realEnd < pageEnd) {
			this.pageEnd = realEnd;
		}
		
		this.prev = this.pageStart > 1;
		this.next = this.pageEnd < realEnd;
	}
    
    
    
}
