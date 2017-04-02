package dao;

import java.util.List;

import model.Ticket;

public interface TicketDao {

	List<Ticket> getBookedTicketList(Integer userId);

	List<Ticket> getNotBookedTicketList(Integer rankId, Integer quantity);

}
