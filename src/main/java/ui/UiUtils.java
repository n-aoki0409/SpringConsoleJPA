package ui;

import org.apache.commons.lang.StringUtils;

public class UiUtils {

	public static boolean isSmallLength(String name, String fieldName, int length) {

		if (name == null) {
			return true;
		}

		if (length < name.length()) {
			System.out.printf( "%sは%s文字以下で入力してください。%n", fieldName, length);
			return false;
		}
		return true;
	}

	public static boolean isNumeric(String str, String fieldName) {
		// 数値か
		if (!StringUtils.isNumeric(str)) {
			System.out.printf("%sは数字で入力してください。%n", fieldName);
			return false;
		}
		return true;
	}

}
