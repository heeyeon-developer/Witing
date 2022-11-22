package com.multi.accom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GyeongiAPITest {

	@Test
	void contextLoads() throws Exception {
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.seoul.go.kr:8088/544f594f4e6264623337554243796b/json/SebcHotelListKor/1/159/"); /* URL */
//		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=ZbDOQPp5hXDSNP7dEFvPIRNEAZ0BisgXmoWTz%2BNJI5F4rWw17ZqbgoeFAo03CLWW0LFfuvo%2BsMUihyZb66O4uw%3D%3D"); /* Service Key */
//		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
//		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
//				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
//		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
//				+ URLEncoder.encode("JSON", "UTF-8")); /* 요청자료형식(XML/JSON)Default: XML */
//		urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8") + "="
//				+ URLEncoder.encode("108", "UTF-8")); /* 108 전국, 109 서울, 인천, 경기도 등 (활용가이드 하단 참고자료 참조) */
//		urlBuilder.append("&" + URLEncoder.encode("tmFc", "UTF-8") + "=" + URLEncoder.encode("202210270600",
//				"UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
//		System.out.println(sb.toString());
		org.json.JSONObject jo = new JSONObject(sb.toString());
		
//		System.out.println(jo.toString());
//		System.out.println("--------------------");
		
		String jsonstring = jo.toString();
		System.out.println(jsonstring);
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(jsonstring);
		JSONObject hotellist = (JSONObject)obj.get("SebcHotelListKor");
		JSONObject row = (JSONObject)hotellist.get("row");
		System.out.println(row);
		
//		for(int i=0; i<row.size();i++) {
//			JSONObject hotel = (JSONObject)row.get(i);
//			String hotelname = (String)hotel.get("NAME_KOR");
//			String addr_gu = (String)hotel.get("H_KOR_GU");
//			String addr_dong = (String)hotel.get("H_KOR_DONG");
//			String hotelcate = (String)hotel.get("CATE3_NAME");
//			
//			System.out.println(i + "번째 호텔 - 호텔명 : " + hotelname + ", 주소 : " + addr_gu + ", " + addr_dong + ", 호텔 등급 : " + hotelcate);
//		}
	}
}

