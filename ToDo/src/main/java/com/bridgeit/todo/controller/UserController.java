package com.bridgeit.todo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.Token.TokenGenerate;
import com.bridgeit.todo.Token.VerifyJwt;
import com.bridgeit.todo.model.ErrorMessage;
import com.bridgeit.todo.model.User;
import com.bridgeit.todo.service.MailService;
import com.bridgeit.todo.service.UserService;
import com.bridgeit.todo.validation.Validator;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MailService mailservice;

	@Autowired
	ErrorMessage errorMessage;

	@Autowired
	Validator validator;

	@Autowired
	TokenGenerate tokenGenerate;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> saveUser(@RequestBody User user, HttpSession session,
			HttpServletRequest request) {

		String isValid = validator.validateUserRegistration(user);
	    //user.setActivated(false);

		if (isValid.equals("true")) {
			boolean isValidate = userService.saveUser(user);

			/* if (isValidate == 0) { */
			if (isValidate == true) {

				String tokenActive = tokenGenerate.generate(user.getId());
				String url = request.getRequestURL().toString();
				url = url.substring(0, url.lastIndexOf("/")) + "/" + "verifyMail" + "/" + tokenActive;

				mailservice.sendMail(user.getEmail(), "mdfirozahmad2222@gmail.com", "emailVerification", url);

				errorMessage.setResponseMessage("registered Successfully....");
				// return new ResponseEntity<String>(isValid, HttpStatus.OK);
				return ResponseEntity.ok(errorMessage);

			}
		} else {
			errorMessage.setResponseMessage(isValid);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		// return new ResponseEntity<String>(isValid, HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/verifyMail/{tokenActive:.+}", method = RequestMethod.GET)
	public ResponseEntity<ErrorMessage> verifyUser(@PathVariable("tokenActive") String tokenActive, HttpServletResponse response)
			throws IOException {
		System.out.println("checking exception");
		User user = null;
		System.out.println(user);
		int id = VerifyJwt.verify(tokenActive);
		System.out.println("verify idee :" +id);
		try {
			user = userService.getUserById(id);
			System.out.println("User details : " + user);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		user.setActivated(true);
		try {
			userService.updateUser(user);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		errorMessage.setResponseMessage("user Email verified successfully ");
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.OK);

		// User user = userService.getUserById(id);

		/*
		 * if(user==null){
		 * errorMessage.setResponseMessage("insert valid information"); return
		 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage); }
		 * errorMessage.setResponseMessage("user activated."); return new
		 * ResponseEntity<ErrorMessage>(HttpStatus.OK);
		 */
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> userLogin(@RequestBody User user, HttpSession session) {
		User userLogin = userService.userLogin(user.getEmail(), user.getPassword());

		if (userLogin == null) {
			errorMessage.setResponseMessage("insert valid information");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		session.setAttribute("user", userLogin);
		errorMessage.setResponseMessage("Login Successfully....");
		return ResponseEntity.ok(errorMessage);
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> forgaotPassword(@RequestBody User user, HttpServletRequest request,
			HttpSession session) {

		String url = request.getRequestURL().toString();
		url = url.substring(0, url.lastIndexOf("/")) + "/" + "setPassword";

		User email = userService.getUserByEmail(user.getEmail());

		if (email == null) {
			errorMessage.setResponseMessage("Enter valid  email...");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

		} else {
			String token = tokenGenerate.generate(user.getId());
			System.out.println("token" + token);
			// session.setAttribute("Token", token);
			mailservice.sendMail(user.getEmail(), "mdfirozahmad2222@gmail.com", "token is :", url);

			errorMessage.setResponseMessage("success");
			return ResponseEntity.ok(errorMessage);
		}
	}

	@RequestMapping(value = "/setPassword", method = RequestMethod.PUT)
	public ResponseEntity<ErrorMessage> setPassword(@RequestBody User user, HttpSession session) {
		System.out.println("inside set passwors");
		//String email = user.getEmail();
		String password = user.getPassword();

		user = userService.getUserByEmail(user.getEmail());

		if (user == null) {
			errorMessage.setResponseMessage("No user Found at this email");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}

		else {
			user.setPassword(password);

			if (userService.setPassword(user)) {
				errorMessage.setResponseMessage("password updated");
				System.out.println("password.." + user.getPassword());
				return ResponseEntity.ok(errorMessage);
			}

			errorMessage.setResponseMessage("password not updated");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(Exception e) {
		return "Exception";

	}
}
