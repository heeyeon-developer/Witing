package com.multi.hytest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class HotelAPItest {
	
	@Test
	void contextLoads() throws IOException, JSONException {
		StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15056048/v1/uddi:ed8f2e7e-b170-4906-b1e8-ccf167cd35a7"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=YRMZelVMO0QavUbVufY2vMQDqr4Pce34Z3yF1GJSrElNqq3FRsfuTkyeaadKnFhS6CwBS3IEU6KPjerfg7qBGg%3D%3D"); /*Service Key*/
        urlBuilder.append("?page=1"); /*페이지번호*/
        urlBuilder.append("&perpage=80"); /*한 페이지 결과 수*/
        urlBuilder.append("&serviceKey=YRMZelVMO0QavUbVufY2vMQDqr4Pce34Z3yF1GJSrElNqq3FRsfuTkyeaadKnFhS6CwBS3IEU6KPjerfg7qBGg%3D%3D"); /*요청자료형식(XML/JSON)Default: XML*/
//        urlBuilder.append("&" + URLEncoder.encode("stnId","UTF-8") + "=" + URLEncoder.encode("108", "UTF-8")); /*108 전국, 109 서울, 인천, 경기도 등 (활용가이드 하단 참고자료 참조)*/
//        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode("202211120600", "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/
        URL url = new URL(urlBuilder.toString());
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        
        ArrayList<ArrayList<String>> info = new ArrayList<>();
        
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        ArrayList<String> addr = new ArrayList<>();
        /////////////////////////////
        if(conn.getResponseCode()==200) { // 정상 호출
			JSONParser jsonparser = new JSONParser();
			try {
				System.out.println(sb);
				JSONObject json = (JSONObject)jsonparser.parse(sb.toString());
				
				JSONArray dataArray = (JSONArray)json.get("data");
				System.out.println(dataArray.size());
				for(int i=0; i<dataArray.size(); i++) {
					JSONObject data = (JSONObject)dataArray.get(i);
					ArrayList<String> temp = new ArrayList<>();
					temp.add((String) data.get("상호명"));
					temp.add((String) data.get("연락처"));
					temp.add((String) data.get("지번주소"));
					
					name.add((String) data.get("상호명"));
					phone.add((String) data.get("연락처"));
					addr.add((String) data.get("지번주소"));
					info.add(temp);
				}
				System.out.println(info);
				System.out.println();
				System.out.println(name);
				System.out.println(phone);
				System.out.println(addr);
////				String description = "";
////				description = (String)data.get("description");
			} catch (Exception e) {
				System.out.println("error");
				e.printStackTrace();
			}
//
//
//		} else {  // 에러 발생
//			System.out.println("Error");
//
		}
        ////////////////////////////
        
        //jsonobject 와 simple을 같이 쓰기 위해서는 앞에다가 쓰
        org.json.JSONObject jo = new org.json.JSONObject(sb.toString()); 
        
        System.out.println(jo.toString());
	}
	
	
}
