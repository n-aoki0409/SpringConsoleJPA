package ui;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import dao.RankDao;

public class SelectRankUi extends AbstractUi {

	@Autowired
	private RankDao rankDao;

	@Autowired
	private InsertReservationUi insertReservationUi;

	@Override
	public void show() {
		// ヘッダーを表示
		showHeader();
		// コンソールからの入力を取得
		Integer eventId = getEventId();
		if (eventId == null) {
			return;
		}
		// ランク一覧を表示
		showRanks(this.rankDao.getRank(eventId));

		this.insertReservationUi.show();
	}

	protected void showRanks(List<Object[]> rankList) {

		System.out.println("--------------------");
		System.out.println("『チケット予約』「チケット一覧」");
		System.out.println("ID   名前   価格   残数");

		for (Object[] objects : rankList) {
			// ランクIDとランク名、価格、残り枚数を表示
			System.out.printf("%s  %s  %s  %s%n", objects[0], objects[1], objects[2], objects[3]);
		}
	}

	protected Integer getEventId() {
		// コンソールからの入力を取得
		String eventId = getInputedString();
		// 文字列が入力されているか
		if (StringUtils.isBlank(eventId)) {
			return null;
		}
		// 数値か
		if (UiUtils.isNumeric(eventId, "ID")) {
			return Integer.valueOf(eventId);
		}
		return getEventId();
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「チケット検索」");
		System.out.println("");
		System.out.println("予約したいイベントのIDを入力し、Enterを押してください。");
		System.out.println("なにも入力せずにEnterを押すとメニューに戻ります。");
	}

}
