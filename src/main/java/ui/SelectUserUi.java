package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Ticket;
import model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import dao.TicketDao;
import dao.UserDao;

public class SelectUserUi extends AbstractUi {

	@Autowired
	private UserDao userDao;

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private DeleteReservationUi deleteReservationUi;

	@Override
	public void show() {
		// ヘッダーを表示
		showHeader();
		// メニューを表示
		showMenu("ユーザー名");
		// コンソールへの入力を取得
		String userName = getInputedString();
		// 文字列が入力されているか
		if (StringUtils.isEmpty(userName)) {
			return;
		}
		// 名前でユーザーを検索
		User user = this.userDao.getUser(userName);
		if (user == null) {
			// 該当するユーザーが存在しない
			System.out.printf("入力されたユーザー名「%s」は存在しませんでした。%n", userName);
			show();
			return;
		}
		// ユーザー情報を表示
		showUser(user);
		// 予約しているチケットを取得
		List<Ticket> ticketList = this.ticketDao.getBookedTicketList(user.getUserId());
		// 予約しているチケット一覧を表示
		showBookedTikectList(ticketList);

		this.deleteReservationUi.show();

	}

	protected void showBookedTikectList(List<Ticket> ticketList) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		System.out.println(" ID  イベント名  日付  価格  予約日時 ");
		for (Ticket ticket : ticketList) {
			// チケットと予約の情報を表示
			System.out.printf("%s  %s  %s  %s  %s%n", ticket.getTicketId(), ticket.getEvent().getName(), dateFormat
					.format(ticket.getEvent().getDate()), ticket.getRank().getPrice(), dateFormat.format(ticket
					.getReservation().getTimestamp()));
		}
	}

	protected void showUser(User user) {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「予約済みチケット一覧」");
		System.out.println("ID    名前");
		System.out.printf("%s  %s%n", user.getUserId(), user.getName());
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「予約済みチケット一覧」");
		System.out.println("");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%sを入力し、Enterを押してください。%n", wanted);
		System.out.println("なにも入力せずにEnterを押すとメニューに戻ります。");
	}
}
