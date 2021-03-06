package com.bridgeit.todo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GoogleUtil {
	
	private static Logger logger = LoggerFactory.getLogger(GoogleUtil.class);
	private static final String googleId="1084118794615-e2ur2memhesr07o5abqi5m55j449aur2.apps.googleusercontent.com";
	private static final String secreateKey="yaj_XZ6p01sATlmbrs8VnBSi";
	private static final String redirectUrl="http://localhost:8080/ToDo/getGoogleLogin";

	public static String generateGoogleUrl() {
		String googleLoginUrl="";
		try {
			googleLoginUrl="https://accounts.google.com/o/oauth2/auth?client_id=" + googleId + "&redirect_uri=" + 
					URLEncoder.encode(redirectUrl, "UTF-8") + "&response_type=code" + "&scope=profile email"
					+ "&approval_prompt=force" + "&access_type=offline";
		} catch (Exception e) {
			logger.info("exception occured if google login url is not valid");
		}
		return googleLoginUrl;
	}

	
	public static String getAccessToken(String code) {
		 String urlParameters = "code=" + code + 
			       "&client_id=" + googleId +
			       "&client_secret=" + secreateKey + 
			       "&redirect_uri=" + URLEncoder.encode(redirectUrl) +
			       "&grant_type=authorization_code";
		 
		 try {
			URL url = new URL("https://accounts.google.com/o/oauth2/token");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String googleResponse = "";
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			
			while((line = bufferedReader.readLine()) != null){
				googleResponse = googleResponse + line;
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			String googleAccessToken;
			try {
				googleAccessToken = objectMapper.readTree(googleResponse).get("access_token").asText();
				logger.info("google aceess token by code:-"+googleAccessToken);
			} catch (IOException e) {
				logger.info("exception occured if access token is null:-");
				e.printStackTrace();
				return null;
			}
			return googleAccessToken;
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return null;
	}


	public static String getProfileData(String accessToken) {
		try {
			URL googleUrlPrfl = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+accessToken);
			URLConnection connection = googleUrlPrfl.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String googleProfileInfo="";
			String line;
			while((line = bufferedReader.readLine())!= null) {
				googleProfileInfo = googleProfileInfo+line; 
			}
			return googleProfileInfo;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("connection refused if exception occured");
			e.printStackTrace();
		}
		return null;
	}
	
}


