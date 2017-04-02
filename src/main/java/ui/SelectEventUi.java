package ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Event;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import dao.EventDao;

public class SelectEventUi extends AbstractUi {

	private static final String DATE_FORMAT_MESSAGE = "日付はYYYYMMDD形式で入力してください。(例:20100101)";

	@Autowired
	private EventDao eventDao;

	@Autowired
	private SelectRankUi selectRankUi;

	@Override
	public void show() {
		// ヘッダーを表示
		showHeader();
		// メニューを表示
		showMenu("検索開始日");
		// コンソールへの入力を取得
		Date start = getDate();
		if (start == null) {
			return;
		}
		// コンソールへの入力を取得
		showMenu("検索終了日");
		Date end = getDate();
		if (end == null) {
			return;
		}
		// 開始日と終了日の大小を検証
		if (start.compareTo(end) > 0) {
			System.out.println("検索終了日には検索開始日より後の日付を入力してください。");
			show();
			return;
		}
		// イベントを取得
		List<Event> eventList = this.eventDao.getEventList(start, end);
		// イベント一覧を表示
		showEventList(eventList);

		this.selectRankUi.show();
	}

	protected void showEventList(List<Event> eventList) {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「イベント一覧」");
		System.out.println("ID    名前    日付");

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		for (Event event : eventList) {
			// イベントIDとイベント名、日付を表示
			System.out.printf("%s  %s  %s%n", event.getEventId(), event.getName(), dateFormat.format(event.getDate()));
		}

	}

	protected Date getDate() {

		// コンソールへの入力を取得
		String dateString = getInputedString();
		// 文字列が入力されているか
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}

		// 8文字(yyyyMMdd)か
		if (dateString.length() != 8) {
			System.out.println(DATE_FORMAT_MESSAGE);
			return getDate();
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date;
		try {
			// Dateに変換
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// 日付に変換できない形式の文字列
			System.out.println(DATE_FORMAT_MESSAGE);
			return getDate();
		}

		// 現在日より未来の日付か
		if (new Date().after(date)) {
			System.out.println("過去の日付は入力できません。もう一度日付を入力してください。");
			return getDate();
		}

		return date;

	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「イベント検索」");
		System.out.println("");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%sを入力し、Enterを押してください。%n", wanted);
		System.out.println("なにも入力せずにEnterを押すとメニューに戻ります。");
		System.out.println(DATE_FORMAT_MESSAGE);
	}

}
