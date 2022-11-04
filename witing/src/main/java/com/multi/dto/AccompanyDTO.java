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
public class AccompanyDTO {
	private int accomid;
	private String custid;
	private String title;
	private String text;
	private Date traveltime;
	private int cnt;
	private float locationx;
	private float locationy;
	

 
}
