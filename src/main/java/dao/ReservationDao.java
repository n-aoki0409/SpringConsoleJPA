package dao;

import model.Reservation;

public interface ReservationDao {

	void addReservation(Reservation reservation);

	void cancelReservation(Reservation reservation);

	Reservation getReservation(Integer reservationId);

}
