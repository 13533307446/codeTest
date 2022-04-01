package com.test;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @since 2022年3月30日
 * @author Zero Zhouxiaoming
 */
public class StringUtils {

	public static final int char_a_index = 'a';

	public static class ConvertConsecutiveCharactersParam {

		private String str;
		private int fromIdx = 0;
		private int toIdx = 0;

		public ConvertConsecutiveCharactersParam(String str, int fromIdx, int toIdx) {
			this.str = str;
			this.fromIdx = fromIdx;
			this.toIdx = toIdx;
		}

		public String getStr() {
			return str;
		}

		public int getFromIdx() {
			return fromIdx;
		}

		public int getToIdx() {
			return toIdx;
		}
	}

	/**
	 * the strategy was used by removing identical char
	 */
	public static Function<ConvertConsecutiveCharactersParam, String> removeConsecutiveCharactersStrategy = (param) -> {
		String tempStr = param.getStr();
		int fromIdx = param.getFromIdx();
		int toIdx = param.getToIdx();

		String preStr = tempStr.substring(0, fromIdx);
		String posStr = toIdx >= tempStr.length() - 1 ? "" : tempStr.substring(toIdx + 1, tempStr.length());
		return preStr + posStr;
	};

	/**
	 * the strategy was used by replacing identical char that comes before it alphabetically
	 */
	public static Function<ConvertConsecutiveCharactersParam, String> replaceConsecutiveCharactersStrategy = (
			param) -> {
		String tempStr = param.getStr();
		int fromIdx = param.getFromIdx();
		int toIdx = param.getToIdx();
		int charIndex = (int) tempStr.charAt(fromIdx);
		
		String toCharString = charIndex <= char_a_index ? "" : Character.valueOf((char) (charIndex - 1)).toString();
		String preStr = tempStr.substring(0, fromIdx);
		String posStr = toIdx >= tempStr.length() - 1 ? "" : tempStr.substring(toIdx + 1, tempStr.length());
		return preStr + toCharString + posStr;
	};

	/**
	 * 
	 * @param str For a given string
	 * @param maxIdenticalCharCont 
	 * @param convertFunction the converted strategy 
	 * @param resultConsumer if find str can be replaced, use the consumer to do special function
	 */
	public static void convertConsecutiveCharacters(String str, int maxIdenticalCharCont,
			Function<ConvertConsecutiveCharactersParam, String> convertFunction, Consumer<String> resultConsumer) {

		// illegal param checking
		if (str == null) {
			return;
		}

		if (maxIdenticalCharCont < 1) {
			throw new IllegalArgumentException("illegal maxIdenticalCharCont");
		}

		if (convertFunction == null) {
			throw new IllegalArgumentException("convertFunction can not null");
		}

		if (str.length() < maxIdenticalCharCont) {
			return;
		}

		String tempStr = str;
		int strIdx = 0;
		int fromIdx = 0;
		int toIdx = 0;
		int identicalCharacterCount = 0;
		Character lastCharacter = null;

		for (; strIdx < tempStr.length(); strIdx++) {
			char currentChar = tempStr.charAt(strIdx);
			if (lastCharacter == null || currentChar != lastCharacter) {
				lastCharacter = currentChar;
				fromIdx = strIdx;
				toIdx = strIdx;
				identicalCharacterCount = 1;
				continue;
			}

			toIdx++;
			identicalCharacterCount++;

			if (identicalCharacterCount == maxIdenticalCharCont) {
				tempStr = convertFunction.apply(new ConvertConsecutiveCharactersParam(tempStr, fromIdx, toIdx));

				if (resultConsumer != null) {
					resultConsumer.accept(tempStr);
				}

				strIdx = fromIdx - maxIdenticalCharCont > 0 ? fromIdx - maxIdenticalCharCont : -1;
				lastCharacter = null;
			}
		}
	}

}
