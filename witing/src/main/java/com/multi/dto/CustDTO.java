package com.multi.dto;

import java.sql.Date;

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
public class CustDTO {

	private String custid;
	private String custpwd;
	private String custname;
	private Date birth;
	private String phone;
	private String email;
	private String addr;
	private String addrdetail;
	private Integer zipcode;
	private Date rdate;
	private String gender;
	private Integer point;
	private String country;
}
