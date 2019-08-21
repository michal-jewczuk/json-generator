package nodomain.jsongenerator.util;

import java.util.Random;

import nodomain.jsongenerator.config.AppConfig;

public class StringUtil {

	public static String generateStringFragment(int length) {
		StringBuilder fragment = new StringBuilder();
		int charCount = AppConfig.STRING_SYMBOLS.length();
		final char[] symbols = AppConfig.STRING_SYMBOLS.toCharArray();
		
		Random rand = new Random();
		int start = rand.nextInt(charCount);
		int step = rand.nextInt(charCount / 3) + 1;
		
		for (int i = 0; i < length; i++) {		
			fragment.append(symbols[start]);
			start = (start + step) % charCount;
		}
		
		return fragment.toString();
	}
	
	public static StringBuilder capitalizeFirstLetter(String fragment) {
		StringBuilder builder = new StringBuilder(fragment.substring(0, 1).toUpperCase());
		builder.append(fragment.substring(1));
		return builder;
	}
}