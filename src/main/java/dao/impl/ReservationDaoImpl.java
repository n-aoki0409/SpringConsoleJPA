package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Reservation;
import dao.ReservationDao;

public class ReservationDaoImpl implements ReservationDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void cancelReservation(Reservation reservation) {
		reservation = em.merge(reservation);
		// 予約を削除
		em.remove(reservation);

	}

	@Override
	public Reservation getReservation(Integer reservationId) {
		// IDで予約を取得
		return em.find(Reservation.class, reservationId);
	}

	@Override
	public void addReservation(Reservation reservation) {
		// 予約を登録
		em.persist(reservation);
	}

}
