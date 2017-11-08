package com.bridgeit.todo.Token;

import java.util.Date;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerate {

	private static String key = "todoApp";

	public String generate(int id) {
		
		long times = 1000 * 60 * 60;
		Date issueDate = new Date();
		Date expDate = new Date(issueDate.getTime() + times);

		JwtBuilder builder = Jwts.builder();

		builder.setSubject("accessToken");
		
		builder.setIssuedAt(issueDate);
		
		builder.setExpiration(expDate);
		
		builder.setIssuer(String.valueOf(id));
		System.out.println("print id is:"+ id);
		
		builder.signWith(SignatureAlgorithm.HS256, key);
		
		String compactJwt = builder.compact();

		return compactJwt;
	}
}
