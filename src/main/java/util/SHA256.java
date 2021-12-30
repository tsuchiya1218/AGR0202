package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHA256 {

	public static String getEncrypt(String pw) {

		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(pw.getBytes());
			byte[] byteDate = md.digest();
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < byteDate.length; i++) {
				sb.append(Integer.toString((byteDate[i] & 0xFF) + 256, 16).substring(1));
			}
			result = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;
	}

}
