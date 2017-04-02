package ui;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public abstract class AbstractUiTemplate extends AbstractUi {

	@Override
	public void show() {
		// メニューを表示
		showMenu();
		// コンソールへの入力を取得
		String inputedString = getInputedString();
		// 入力文字列の検証
		if (isValidNumber(inputedString)) {
			// 処理を実行
			execute(NumberUtils.toInt(inputedString));
		}
	}

	abstract protected void showMenu();

	abstract protected int getMaxMenuNumber();

	abstract protected int getMinMenuNumber();

	abstract protected void execute(int number);

	protected boolean isValidNumber(String str) {

		// 文字列が入力されているか
		if (StringUtils.isBlank(str)) {
			return false;
		// 数値か
		} else if (!StringUtils.isNumeric(str)) {
			return false;
		}
		// 数値なのでintに変換
		int number = NumberUtils.toInt(str);
		// 数値がメニューに表示されている番号の範囲か
		if (getMinMenuNumber() <= number && number <= getMaxMenuNumber()) {
			return true;
		}
		return false;
	}
}
