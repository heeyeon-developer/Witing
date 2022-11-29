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
public class PlanDTO {
	private Integer planid;
	private Integer accomid;
	private String planname;
	private float planx;
	private float plany;
	private int idx;
	private String todo;

	private String custid;
	private String title;
	private String accomtext;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date traveltime;
	private int cnt;
	private float locationx;
	private float locationy;
	private String country;
	private String gender;
	private Date birth;
	private String certification;
	private String type;
 
}
