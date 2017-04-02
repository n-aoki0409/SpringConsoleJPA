package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer eventId;

	private String name;

	private Date date;

	private Set<Ticket> tickets;

	private Set<Rank> ranks;

	@Id
	@Column(name = "event_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@OneToMany(mappedBy = "event")
	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	@OneToMany(mappedBy = "event")
	public Set<Rank> getRanks() {
		return this.ranks;
	}

	public void setRanks(Set<Rank> ranks) {
		this.ranks = ranks;
	}

	public String toString() {
		return new ToStringBuilder(this).append("eventId", getEventId()).append("name", getName()).append("date",
				getDate()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof Event))
			return false;
		Event castOther = (Event) other;
		return new EqualsBuilder().append(this.getEventId(), castOther.getEventId()).append(this.getName(),
				castOther.getName()).append(this.getDate(), castOther.getDate()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getEventId()).append(getName()).append(getDate()).toHashCode();
	}

}
