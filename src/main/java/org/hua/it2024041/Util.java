// provided by the project instrucions
// this class is used to provide utility methods for the project, such as hashing passwords or other sensitive data
// it is a final class, so it cannot be extended, and it has a private constructor, so it cannot be instantiated
package org.hua.it2024041;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Util {

	/** 
	 * Ensure that we will not create an instance of this class 
	 */
	private Util() {
		
	}
	
	/**
	 * SHA (Secure Hash Algorithm) 256
	 * @see <a href="https://www.baeldung.com/sha-256-hashing-java">Read more...</a>
	 * This method takes a string as input and returns its SHA-256 hash as a hexadecimal string.
	 * If the input string is null, it returns null.
	 * If the SHA-256 algorithm is not available, it throws a RuntimeException.
	 */
	public static String getHash256(final String str) {
		if (str == null) return null;
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(encodedhash);
		} catch(NoSuchAlgorithmException e) {
			throw new RuntimeException("getHash256() problem !", e);
		}
	}
	
	/*
	* This method converts a byte array to a hexadecimal string. It iterates through each byte in the array,
	* converts it to a hexadecimal string, and appends it to a StringBuilder. If the hexadecimal string is only one character long, it prepends a '0' to
	* ensure that each byte is represented by two characters. Finally, it returns the complete hexadecimal string.
	*/
	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
	    return hexString.toString();
	}

	
}
