package scapi.common.helper;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncodeUtil {

	public static String getMD5(String input) {
		MessageDigest md;
		String cbString = "";
		try {
			md = MessageDigest.getInstance("MD5");
			log.info("before encode : {}",input);
			md.update(input.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			cbString = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cbString;
	}

}
