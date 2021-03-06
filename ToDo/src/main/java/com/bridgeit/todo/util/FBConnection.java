package com.bridgeit.todo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FBConnection {
	
	private static  Logger logger = LoggerFactory.getLogger(FBConnection.class);
	private static final String facebookId="383534318755042";
	private static final String facbooksecreateKey="34c6bdae1ac59fd0f39cb47f4f57c018";
	private static final String fbredirectUrl="http://localhost:8080/ToDo/getFacebookLogin";
	private static final String USER_ACCESS_URL = "https://graph.facebook.com/v2.9/me?access_token=";
	private static final String BINDING = "&fields=id,name,email,first_name,last_name,picture";
	
	@SuppressWarnings("deprecation")
	public static String generateFbUrl() {
		String fbLoginUrl="";
		
		try {
			fbLoginUrl = "https://www.facebook.com/v2.10/dialog/oauth?" + "client_id=" + facebookId + "&redirect_uri="
					+ URLEncoder.encode(fbredirectUrl) + "&state=todoappstate" + "&scope=public_profile,email";
			 logger.info("URL for FB"+fbLoginUrl);
		} catch (Exception e) {
			logger.error("catching exception e");
		}
		return fbLoginUrl;
		
	}
	public static String getAccessFbToken(String code) {
		@SuppressWarnings("deprecation")
		String fbUrlParameters = "&redirect_uri=" + URLEncoder.encode(fbredirectUrl)
		+ "&client_id=" + facebookId 
		+ "&client_secret=" + facbooksecreateKey 
		+ "&code=" + code;
		
		try {
			URL url = new URL("https://graph.facebook.com/v2.10/oauth/access_token?");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
	
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(fbUrlParameters);
			writer.flush();
			
			String fbResponse = "";
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				fbResponse = fbResponse + line;
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			String fbAccessToken = objectMapper.readTree(fbResponse).get("access_token").asText();
			logger.info("fb access token:-"+fbAccessToken);
			
			return fbAccessToken;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getFacebookProfInfo(String fbAccessToken) {
		
		try {
			URL fbURL = new URL(USER_ACCESS_URL+fbAccessToken+BINDING);
			
			logger.info("getting fbURL");
			URLConnection connection = fbURL.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String fbProfileInfo="";
			String line;
			while((line=bufferedReader.readLine())!=null) {
				fbProfileInfo = line +fbProfileInfo;
			}
			return fbProfileInfo;
		} catch (Exception e) {
			logger.info("exception for url");
		}
		return null;
	}

}

