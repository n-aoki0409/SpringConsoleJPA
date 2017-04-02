package ui;

import model.Reservation;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ReservationDao;

public class DeleteReservationUi extends AbstractUi {

	@Autowired
	private ReservationDao reservationDao;

	@Transactional
	@Override
	public void show() {
		// ヘッダーを表示
		showHeader();
		// コンソールからの入力を取得
		Integer reservationId = getReservationId();
		if (reservationId == null) {
			return;
		}
		// IDで予約を検索
		Reservation reservation = this.reservationDao.getReservation(reservationId);
		if (reservation == null) {
			// 該当する予約は存在しない
			System.out.println("入力されたIDを持つ予約は存在しませんでした。");
			show();
		}
		// 予約を取り消し
		this.reservationDao.cancelReservation(reservation);
		System.out.printf("ID「%s」の予約を取り消しました。%n", reservationId);
	}

	protected Integer getReservationId() {
		// コンソールからの入力を取得
		String reservationId = getInputedString();
		// 文字列が入力されているか
		if (StringUtils.isBlank(reservationId)) {
			return null;
		}
		// 数値か
		if (StringUtils.isNumeric(reservationId)) {
			return Integer.valueOf(reservationId);
		}
		System.out.println("IDは数字で入力してください。");

		return getReservationId();
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『チケット予約』「予約取り消し」");
		System.out.println("");
		System.out.println("予約を取り消したいチケットのIDを入力し、Enterを押してください。");
		System.out.println("なにも入力せずにEnterを押すとメニューに戻ります。");
	}
}
