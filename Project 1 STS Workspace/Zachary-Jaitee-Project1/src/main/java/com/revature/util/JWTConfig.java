package com.revature.util;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.SignatureAlgorithm;

public class JWTConfig {
	public static final String URI = "/**";
	public static final String HEADER = "Authorization";
	public static final String PREFIX = "CoolKids ";
	public static final int EXPIRATION = 24 * 60 * 60;
	public static final String SECRET = "Zubadank";
	public static final Key signingKey;
	
	static {
		// Create a signing key using the JWT secret key
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] secretBytes = DatatypeConverter.parseBase64Binary(SECRET);
		signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
	}
	
	private JWTConfig() {
		super();
	}
}
