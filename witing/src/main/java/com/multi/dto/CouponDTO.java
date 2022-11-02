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
public class CouponDTO {
	private int couponid;
	private String custid;
	private String kind;
	private int discountprice;
	private int discountpercent;
}
