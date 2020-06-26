package pl.lodz.p.edu.userInterface;

import pl.lodz.p.edu.model.Clients.UserClient;
import pl.lodz.p.edu.model.Tickets.Ticket;

import java.util.List;
import java.util.UUID;

public interface ShowAllUserClientTicketUseCase {
    List<Ticket> getTickets(UUID u);
}
