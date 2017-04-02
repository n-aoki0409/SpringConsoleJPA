package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ticketId;

	private Reservation reservation;

	private Rank rank;

	private Event event;

	@Id
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	@OneToOne(mappedBy = "ticket")
	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@ManyToOne
	@JoinColumn(name = "rank_id")
	public Rank getRank() {
		return this.rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	@ManyToOne
	@JoinColumn(name = "event_id")
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String toString() {
		return new ToStringBuilder(this).append("ticketId", getTicketId()).append("rank", getRank()).append("event",
				getEvent()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof Ticket))
			return false;
		Ticket castOther = (Ticket) other;
		return new EqualsBuilder().append(this.getTicketId(), castOther.getTicketId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getTicketId()).toHashCode();
	}

}
