package com.multi.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReplyDTO {
	private int replyid;
	private int accomid;
	private String custid;
	private String comment;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date cdate;
	private int topid;
 
}
