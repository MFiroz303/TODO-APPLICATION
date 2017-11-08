package com.bridgeit.util;

import java.security.MessageDigest;

public class Encryption {

	public String encryptPassword(String password) {

		String generatedPassword = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] passBytes = md.digest();
			System.out.println(passBytes);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < passBytes.length; i++) {
				sb.append(Integer.toString((passBytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return generatedPassword;
	}
}
