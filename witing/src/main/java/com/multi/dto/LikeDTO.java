package com.multi.dto;

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
public class LikeDTO {
	private int likeid;
	private int hotelid;
	private String custid;
	
	private int cateid;
	private String hotelname;
	private String detail;
	private String hotelimg1;
	private String hotelimg2;
	private String feature;
	private int cityid;
	private String name;
	
	
	
 
}
