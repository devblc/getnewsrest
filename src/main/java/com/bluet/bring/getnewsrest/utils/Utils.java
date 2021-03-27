package com.bluet.bring.getnewsrest.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Common routines
 **/
public class Utils {

	/**
	 * Generate sequence in has bytes format
	 * @param String input that will be converted to hash number
	 * @param Hash   name example: SHA-256, MD5
	 **/
	public byte[] generateSequence(String seq, String hash) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(hash);
		} catch (NoSuchAlgorithmException e) {
			System.out.print(e.getMessage());
		}
		return md.digest(seq.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Convert byte array into hashString representation
	 * @param String input that will be converted to hash number
	 * @param Hash   name example: SHA-256, MD5
	 **/
	public String toHexString(byte[] hash) {
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));

		return hexString.toString();
	}

}
