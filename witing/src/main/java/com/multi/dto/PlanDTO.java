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
public class PlanDTO {
	private int planid;
	private int accomid;
	private float planx;
	private float plany;
	private int idx;
	private String todo;

	private String planname;
	private String custid;
	private String title;
	private String text;
	private Date traveltime;
	private int cnt;
	private float locationx;
	private float locationy;
	private String country;
	private String gender;
	private Date birth;
 
}
