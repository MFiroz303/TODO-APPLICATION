package com.bridgeit.todo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.Token.TokenGenerate;
import com.bridgeit.todo.model.ErrorMessage;
import com.bridgeit.todo.model.User;
import com.bridgeit.todo.service.UserService;
import com.bridgeit.todo.util.GoogleUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GoogleLoginController {

	private Logger logger = (Logger) LoggerFactory.getLogger(GoogleLoginController.class);
	
	@Autowired
	 UserService userService;
	
	@Autowired
	ErrorMessage errorMessage;
	
	@RequestMapping(value="/googleLogin")
	public void googleLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String googleUrl=GoogleUtil.generateGoogleUrl();
		logger.info("checking google url"+googleUrl);
		try {
			response.sendRedirect(googleUrl);
		} catch (IOException e) {
			logger.info("exception while generating google url");
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/getGoogleLogin")
	public ResponseEntity<ErrorMessage> getGoogleLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		String code = (String)request.getParameter("code");
		logger.info("code"+code);
		String accessToken = GoogleUtil.getAccessToken(code);
		logger.info("accessToken"+accessToken);
		String googleProfileInfo = GoogleUtil.getProfileData(accessToken);
		logger.info("google profile info"+googleProfileInfo);
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			String email = objectMapper.readTree(googleProfileInfo).get("email").asText();
			logger.info("email:-"+email);
			User user = userService.getUserByEmail(email);
			if(user==null) {
				
				User googleUser = new User();
				googleUser.setEmail(email);
				
				String firstName = objectMapper.readTree(googleProfileInfo).get("given_name").asText();
				googleUser.setFirstName(firstName);
			
				String lastName = objectMapper.readTree(googleProfileInfo).get("family_name").asText();
				googleUser.setLastName(lastName);
				
				String profilePic = objectMapper.readTree(googleProfileInfo).get("picture").asText();
				googleUser.setProfilePic(profilePic);
				
				googleUser.setActivated(true);
				int  userId= userService.saveUser(googleUser); 
		     	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+userId);
		     	
				String myaccessToken = TokenGenerate.generate(userId);
			    session.setAttribute("todoAppAccessToken", myaccessToken);
				response.sendRedirect("http://localhost:8080/ToDo/#!/dummy");
					
			} else {	
				System.out.println("#############################"+user.getId());
				String myaccessToken = TokenGenerate.generate(user.getId());
				logger.info("token geneted by jwt" + myaccessToken);
				session.setAttribute("todoAppAccessToken", myaccessToken);
				response.sendRedirect("http://localhost:8080/ToDo/#!/dummy");

			}
			
		} catch (IOException e) {
			logger.info("exception occured during registering user from fb:");
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getToken",method = RequestMethod.GET )
	public ResponseEntity<ErrorMessage> getSocialLoginToken(HttpSession session)
	{
	String token=(String) session.getAttribute("todoAppAccessToken");
	errorMessage.setResponseMessage(token);
	System.out.println("social token "+ token);
	/*if(token!=null)
	{
	return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
	}*/
	return ResponseEntity.status(HttpStatus.OK).body(errorMessage);

	}
}