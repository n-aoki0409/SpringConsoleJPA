package ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MenuUi extends AbstractUiTemplate {

	@Autowired
	private SelectEventUi selectEventUi;

	@Autowired
	private SelectUserUi selectUserUi;

	@Autowired
	private UpdateUserUi updateUserUi;

	@Override
	protected void execute(int number) {
		switch (number) {
		case 1:
			// 1.終了
			System.out.println("終了しました。");
			System.exit(0);
		case 2:
			// 2.イベント検索
			this.selectEventUi.show();
			break;
		case 3:
			// 3.予約済みチケット一覧
			this.selectUserUi.show();
			break;
		case 4:
			// 4.ユーザー名変更
			this.updateUserUi.show();
			break;
		}
	}

	@Override
	protected int getMaxMenuNumber() {
		return 4;
	}

	@Override
	protected int getMinMenuNumber() {
		return 1;
	}

	@Override
	protected void showMenu() {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「メニュー」");
		System.out.println("");
		System.out.println("1.終了");
		System.out.println("2.イベント検索");
		System.out.println("3.予約済みチケット一覧");
		System.out.println("4.ユーザー名変更");
		System.out.println("");
		System.out.println("番号を入力し、Enterを押してください。");
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// Spring設定ファイルの読み込み
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MenuUi menuUi = (MenuUi) context.getBean("menuUi");
		while (true) {
			menuUi.show();
		}
	}

}
