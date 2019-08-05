package com.yzd.jutils.unicode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 *  unicode 工具类 
 * @author wangwenteng
 *
 */
public class UnicodeUtils {
	
	public static String unicodeToString(String unicodeStr){
		String str = unicodeStr;
		if (StringUtils.isNotEmpty(unicodeStr)) {
			Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
			Matcher matcher = pattern.matcher(unicodeStr);
			char ch;
			while (matcher.find()) {
				ch = (char) Integer.parseInt(matcher.group(2), 16);
				str = str.replace(matcher.group(1), ch + "");
			}
		}
		return str;
	}

	public static void main(String[] args) {
		System.out.println(unicodeToString("\\u8BA1\\u7B97\\u673A\\u5B66\\u9662a--aaaa\\u8F6F\\u4EF6\\u4E00\\u73ED"));
	}
}
