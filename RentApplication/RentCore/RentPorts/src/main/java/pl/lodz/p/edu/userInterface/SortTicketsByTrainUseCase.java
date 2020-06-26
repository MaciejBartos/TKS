package pl.lodz.p.edu.userInterface;

import pl.lodz.p.edu.model.Tickets.Ticket;

import java.util.List;

public interface SortTicketsByTrainUseCase {
    List<Ticket> sortTrains(String text);
}
