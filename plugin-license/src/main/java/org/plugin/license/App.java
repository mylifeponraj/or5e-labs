package org.plugin.license;

import java.nio.charset.Charset;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class App {

	public static void main(String[] args) {
		//String userName = "Ponraj Suthanthiramani";
		String userName = "mylife.ponraj@gmail.com";
		String productKey = "ARDUINO-UNO";
		String versionNumber = "1";

		final String licenseKey = createLicenseKey(userName, productKey, versionNumber);
		System.out.println("licenseKey = " + licenseKey);

	}

	public static String createLicenseKey(String userName, String productKey, String versionNumber) {
		final String s = userName + "|" + productKey + "|" + versionNumber;
		final HashFunction hashFunction = Hashing.sha1();
		final HashCode hashCode = hashFunction.hashString(s, Charset.forName("UTF-8"));
		final String upper = hashCode.toString().toUpperCase();
		return group(upper);
	}

	private static String group(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			if (i % 6 == 0 && i > 0) {
				result += '-';
			}
			result += s.charAt(i);
		}
		return result;
	}

}
