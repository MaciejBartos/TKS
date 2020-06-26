package pl.lodz.p.edu.userInterface;

import pl.lodz.p.edu.model.Tickets.Ticket;

import java.util.List;

public interface SortTicketsByUserClientUseCase {
    List<Ticket> sortUsers(String text);
}
