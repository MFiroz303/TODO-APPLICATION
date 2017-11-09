package com.bridgeit.todo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.tod.util.FBConnection;
import com.bridgeit.todo.Token.TokenGenerate;
import com.bridgeit.todo.model.ErrorMessage;
import com.bridgeit.todo.model.User;
import com.bridgeit.todo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class FBLoginController {
	private Logger logger = (Logger) LoggerFactory.getLogger(FBLoginController.class);

	@Autowired
	UserService userService;

	@Autowired
	ErrorMessage errorMessage;

	@RequestMapping(value = "/facebookLogin")
	public void fbLogin(HttpServletRequest request, HttpServletResponse response) {
		String facbookUrl = FBConnection.generateFbUrl();
		try {
			logger.info("FB URL: " + facbookUrl);
			response.sendRedirect(facbookUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getFacebookLogin", method = RequestMethod.GET)
	public ResponseEntity<ErrorMessage> getFbAccessToken(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		
		
		String facebookbCode = request.getParameter("code");
		logger.info("facebookbCode:-" + facebookbCode);
		String accessFbToken = FBConnection.getAccessFbToken(facebookbCode);
		logger.info("accessFbToken:-" + accessFbToken);
		
		String facebookProfInfo = FBConnection.getProfileInfoFromFb(accessFbToken);
		logger.info(facebookProfInfo);
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String email = mapper.readTree(facebookProfInfo).get("email").asText();
			User user = userService.getUserByEmail(email);
			logger.info("getUserByEmail:-" + user);
			
			if (user == null) {
				
				User facebookUser = new User();
				facebookUser.setEmail(mapper.readTree(facebookProfInfo).get("email").asText());
				facebookUser.setFirstName(mapper.readTree(facebookProfInfo).get("first_name").asText());
				facebookUser.setLastName(mapper.readTree(facebookProfInfo).get("last_name").asText());
				response.sendRedirect("http://localhost:8080/todo/#!/homePage");
				
			} else {
				
				String accessToken = TokenGenerate.generate(user.getId());
				logger.info("token geneted by jwt" + accessToken);
				session.setAttribute("AccessToken", accessToken);
				response.sendRedirect("http://localhost:8080/todo/#!/dummyFbLogin");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("exception occured during registering user from fb:");
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/tokenAftergFbLogin")
	public ResponseEntity<ErrorMessage> getAccessTokenByglogin(HttpSession session) {
		String acessToken = (String) session.getAttribute("accessToken");
		errorMessage.setResponseMessage(acessToken);
		return ResponseEntity.ok(errorMessage);

	}

}
