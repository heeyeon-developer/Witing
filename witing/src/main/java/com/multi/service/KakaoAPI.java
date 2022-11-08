package com.multi.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoAPI {

	public String getAccessToken (String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		String line = "", result = "";
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//POST 요청을 위해 기본값이 false인 setOutput을 true로 셋팅
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			//POST 요청할 때에 필요한 파라미터를 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=028dd45a5b014d0ffd5d4acc640599d4");
			sb.append("&redirect_uri=http://localhost/kakaologin");
			sb.append("&code="+authorize_code);
			bw.write(sb.toString());
			bw.flush();
			//결과 코드가 200일 경우 성공
			System.out.println("responseCode : "+conn.getResponseCode());
			//요청을 통해 얻은 JSON타입의 Response 메세지
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("responseBody : "+result);
			//JSON 데이터 파싱
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("access_token : "+access_Token);
			System.out.println("refresh_token : "+refresh_Token);
			br.close();
			bw.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return access_Token;
	}
	
	public HashMap<String, String> getUserInfo (String access_Token){
		HashMap<String, String> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		String line = "", result = "";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			//요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer "+access_Token);
			System.out.println("responseCode : "+conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			System.out.println(br);
			while((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : "+result);
			
			JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(result);
	        
	        JsonObject kakao_properties = element.getAsJsonObject().get("properties").getAsJsonObject();	
	        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();	
	        
	        String birthday = kakao_account.get("birthday").toString().replaceAll("\"","");
	        userInfo.put("name", kakao_properties.get("nickname").toString().replaceAll("\"",""));
	        userInfo.put("email", kakao_account.get("email").toString().replaceAll("\"",""));
	        userInfo.put("birthday", birthday.substring(0,2)+"-"+birthday.substring(2,4));
	        userInfo.put("gender", kakao_account.get("gender").toString().replaceAll("\"","").equals("female")? "woman" : "man");
	        
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	public void kakaoLogout(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println(result);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
