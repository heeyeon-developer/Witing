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
public class CateDTO {
	private int cateid;
	private int topid;
	private String catename;
	
	private int hotelid;
	private int cityid;
	private String hotelname;
	private String name;
	
 
}
