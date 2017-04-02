package model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer reservationId;

	private Timestamp timestamp;

	private Ticket ticket;

	private User user;

	@Id
	@Column(name = "reservation_id")
	public Integer getReservationId() {
		return this.reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	@Column(name = "reserved_ts")
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@OneToOne
	@JoinColumn(name = "reservation_id")
	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		return new ToStringBuilder(this).append("reservationId", getReservationId()).append("user", getUser())
				.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof Reservation))
			return false;
		Reservation castOther = (Reservation) other;
		return new EqualsBuilder().append(this.getReservationId(), castOther.getReservationId()).append(
				this.getTimestamp(), castOther.getTimestamp()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getReservationId()).append(getTimestamp()).toHashCode();
	}

}
