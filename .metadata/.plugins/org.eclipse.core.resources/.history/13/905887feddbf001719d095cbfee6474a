package com.bridgeit.todo.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenVerify {

	String key="secretKey";
	
	//Sample method to validate and read the JWT
	@SuppressWarnings("unused")
	private void parseJWT(String jwt) {
	 
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(key)
	       .parseClaimsJws(jwt).getBody();
	    System.out.println("ID: " + claims.getId());
	    System.out.println("Subject: " + claims.getSubject());
	    System.out.println("Issuer: " + claims.getIssuer());
	    System.out.println("Expiration: " + claims.getExpiration());
	}

	@Override
	public String toString() {
		return "TokenVerify [key=" + key + "]";
	}

}
