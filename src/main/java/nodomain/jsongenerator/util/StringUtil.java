package nodomain.jsongenerator.util;

import java.util.Random;

public class StringUtil {

	public static String generateStringFragment(int length, String symbolsString) {
		StringBuilder fragment = new StringBuilder();
		int charCount = symbolsString.length();
		final char[] symbols = symbolsString.toCharArray();
		
		Random rand = new Random();
		int start = rand.nextInt(charCount);
		int step = rand.nextInt(charCount / 5) + 1;
		
		for (int i = 0; i < length; i++) {		
			fragment.append(symbols[start]);
			start = (start + step + i / 2) % charCount;
		}
		
		return fragment.toString();
	}
	
	public static StringBuilder capitalizeFirstLetter(String fragment) {
		StringBuilder builder = new StringBuilder(fragment.substring(0, 1).toUpperCase());
		builder.append(fragment.substring(1));
		return builder;
	}
}
