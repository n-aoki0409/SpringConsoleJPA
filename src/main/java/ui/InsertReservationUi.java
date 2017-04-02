package ui;

import java.util.List;

import model.Reservation;
import model.Ticket;
import model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ReservationDao;
import dao.TicketDao;
import dao.UserDao;

public class InsertReservationUi extends AbstractUi {

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ReservationDao reservationDao;

	@Transactional
	@Override
	public void show() {
		// ヘッダーを表示
		showHeader();
		// コンソールからの入力を取得
		Integer rankId = getNumber();
		if (rankId == null) {
			return;
		}
		// メニューを表示
		showMenu("チケットの枚数");
		System.out.println("なにも入力せずにEnterを押すとメニューに戻ります。");
		// コンソールからの入力を取得
		Integer quantity = getNumber();
		if (quantity == null) {
			return;
		}
		// ランクIDで予約されていないチケットの枚数を検索
		List<Ticket> ticketList = this.ticketDao.getNotBookedTicketList(rankId, quantity);
		if (ticketList == null) {
			// 該当するチケットが存在しない
			System.out.println("入力されたIDを持つランクのチケットはありませんでした。もう一度入力してください。");
			show();
			return;
		}

		if (ticketList.size() < quantity.intValue()) {
			// チケットが入力された枚数分残っていない
			System.out.println("チケットが入力された枚数分ありませんでした。もう一度入力してください。");
			show();
			return;
		}
		// メニューを表示
		showMenu("名前");
		// コンソールからの入力を取得
		String name = getInputedString();
		// 文字列が入力されているか
		if (StringUtils.isBlank(name)) {
			return;
		}
		// ユーザー名でユーザーを検索
		User user = this.userDao.getUser(name);
		if (user == null) {
			// 新規ユーザーを生成
			user = new User();
			user.setName(name);
			this.userDao.addUser(user);
		}
		// 予約処理を実行
		reserve(ticketList, user);
	}

	protected void reserve(List<Ticket> ticketList, User user) {
		
		for (Ticket ticket : ticketList) {
			// 新規予約を生成
			Reservation reservation = new Reservation();
			reservation.setReservationId(ticket.getTicketId());
			reservation.setTicket(ticket);
			reservation.setUser(user);

			// データベースに予約を登録
			this.reservationDao.addReservation(reservation);
		}
	}

	protected Integer getNumber() {
		// コンソールからの入力を取得
		String number = getInputedString();
		// 文字列が入力されているか
		if (StringUtils.isBlank(number)) {
			return null;
		}
		// 数値か
		if (StringUtils.isNumeric(number)) {
			return Integer.valueOf(number);
		}
		System.out.println("数字で入力してください。");

		return getNumber();
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「チケット」");
		System.out.println("");
		showMenu("予約したいチケットのID");
		System.out.println("なにも入力せずにEnterを押すとメニューに戻ります。");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%sを入力し、Enterを押してください。%n", wanted);
	}
}
