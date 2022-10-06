package com.nttdata.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashPassword {

	public static String hashPassword(String password) {

		String salt = BCrypt.gensalt(10);
		String hashedPassword = BCrypt.hashpw(password, salt);

		return hashedPassword;
	}

}
