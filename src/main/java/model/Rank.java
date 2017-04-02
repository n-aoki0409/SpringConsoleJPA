package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Rank implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer rankId;

	private String name;

	private Integer price;

	private Event event;

	private Set<Ticket> tickets;

	@Id
	@Column(name = "rank_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRankId() {
		return this.rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@ManyToOne
	@JoinColumn(name = "event_id")
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@OneToMany(mappedBy = "rank")
	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String toString() {
		return new ToStringBuilder(this).append("rankId", getRankId()).append("name", getName()).append("price",
				getPrice()).append("event", getEvent()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof Rank))
			return false;
		Rank castOther = (Rank) other;
		return new EqualsBuilder().append(this.getRankId(), castOther.getRankId()).append(this.getName(),
				castOther.getName()).append(this.getPrice(), castOther.getPrice()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getRankId()).append(getName()).append(getPrice()).toHashCode();
	}

}
