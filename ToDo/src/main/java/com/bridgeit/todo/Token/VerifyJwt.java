package com.bridgeit.todo.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class VerifyJwt {

  private static String key="todoApp";
	
	//Sample method to validate and read the JWT
	public static int verify(String token) {
	 System.out.println("token verify   "+token);
	    //This line will throw an exception if it is not a signed JWS (as expected)
	   try {
		   Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();  
		    return Integer.parseInt(claims.getId());
	} catch (Exception e) {
		e.printStackTrace();
		 return 0;
	}
	}
}
