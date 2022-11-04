package com.multi.dto;



import java.util.Date;

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
public class PostDTO {
	private int postid;
	private String custid;
	private String adminid;
	private int hotelid;
	private String title;
	private String text;
	private String img;
	private Date rdate;
	private String kind;
	private int star;
}
