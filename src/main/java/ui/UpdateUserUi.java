package ui;

import model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;

public class UpdateUserUi extends AbstractUi {

	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public void show() {
		// ヘッダーを表示
		showHeader();
		// コンソールからの入力を取得
		Integer id = getUserId();
		if (id == null) {
			return;
		}
		// IDでユーザーを検索
		User user = this.userDao.getUser(id);
		if (user == null) {
			// 該当するユーザーが存在しない
			System.out.printf("入力されたユーザーID「%s」は存在しませんでした。%n", id);
			show();
			return;
		}
		// ユーザー情報を表示
		showUser(user);
		// コンソールからの入力を表示
		String name = getName();
		// 文字列が入力されているか
		if (StringUtils.isBlank(name)) {
			return;
		}
		// ユーザー名をセット
		user.setName(name);
		// データベースを更新
		// this.userDao.updateUser(user);
	}

	protected Integer getUserId() {

		final String userId = "ユーザーID";
		// メニューを表示
		showMenu(userId);
		// コンソールからの入力を取得
		String id = getInputedString();
		// 文字列が入力されているか
		if (StringUtils.isBlank(id)) {
			return null;
		}
		// 数値か
		if (UiUtils.isNumeric(id, userId)) {
			return new Integer(id);
		}

		return getUserId();
	}

	protected String getName() {

		showMenu("新しいユーザー名");
		// コンソールからの入力を取得
		String newName = getInputedString();
		// 128文字以下か
		if (!UiUtils.isSmallLength(newName, "ユーザー名", 128)) {
			return getName();
		}

		return newName;
	}

	protected void showUser(User user) {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「ユーザー情報変更」");
		System.out.println("ID    名前");
		System.out.printf("%s  %s%n", user.getUserId(), user.getName());
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「ユーザー情報変更」");
		System.out.println("");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%sを入力し、Enterを押してください。%n", wanted);
		System.out.println("なにも入力せずにEnterを押すとメニューに戻ります。");
	}
}