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
public class RoomDTO {
	private int roomid;
	private int hotelid;
	private String roomtype;
	private String bedtype;
	private int price;
	private String roomimg1;
	private String roomimg2;
	private int max;
	private int standard;
	private int addprice;
 
}
