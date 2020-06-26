package pl.lodz.p.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Clients.UserClient;
import pl.lodz.p.edu.model.Tickets.Ticket;

import pl.lodz.p.edu.userInterface.*;

import java.time.LocalDate;
import java.util.*;

@Service
public class TicketServices implements FinishAllocationUseCase, ShowAllUserClientTicketUseCase, SaveItemUseCase<Ticket>, ShowItemUseCase<Ticket>, ShowAllItemsUseCase<Ticket>, SortTicketsByTrainUseCase, SortTicketsByUserClientUseCase, RemoveTicketUseCase {

    private final UserClientService userService;
    private final TrainServices trainService;
    private final IAddItem<Ticket> ticketAdd;
    private final IGetAllItems<Ticket> ticketsGet;
    private final IGetItem<Ticket> ticketGet;
    private final IDeleteItem<Ticket> ticketDel;
    private final ISortItems<Ticket> ticketSort;
    private final IUpdateItem<Ticket> ticketUp;

    @Autowired
    public TicketServices(UserClientService userService, TrainServices trainService, IAddItem<Ticket> ticketAdd, IGetAllItems<Ticket> ticketsGet, IGetItem<Ticket> ticketGet, IDeleteItem<Ticket> ticketDel, ISortItems<Ticket> ticketSort, IUpdateItem<Ticket> ticketUp) {
        this.userService = userService;
        this.trainService = trainService;
        this.ticketAdd = ticketAdd;
        this.ticketsGet = ticketsGet;
        this.ticketGet = ticketGet;
        this.ticketDel = ticketDel;
        this.ticketSort = ticketSort;
        this.ticketUp = ticketUp;
    }

    public void add(Ticket t) throws Exception {
        if (trainService.get(t.getTrain().getTrainId()) == null || trainService.get(t.getTrain().getTrainId()).getTicketID() != null) {
            throw new Exception("train error");
        }
        t.setUser(userService.get(t.getUser().getUserId()));
        t.setTrain(trainService.get(t.getTrain().getTrainId()));
        t.getTrain().setTicketID(t.getTicketId());
        trainService.update(t.getTrain());
        ticketAdd.add(t);
    }

    public void delete(UUID id) {
        Ticket t = get(id);
        if (t.getEndingDate() == null) {
            try {
                ticketDel.delete(t);
            } catch (Exception ignore) {
            }
            t.getTrain().setTicketID(null);
            trainService.update(t.getTrain());
        }
    }

    public List<Ticket> getAll() {
        return ticketsGet.getAll();
    }

    public Ticket get(UUID id) {
        Optional<pl.lodz.p.edu.model.Tickets.Ticket> ticket = ticketGet.getById(id);
        if (ticket.isPresent()) {
            return ticket.get();
        }
        return null;

    }

    public void endTicket(UUID id) {
        Ticket t = get(id);
        if (!t.getStartingDate().isAfter(LocalDate.now())) {
            t.setEndingLocalDate(LocalDate.now());
            if (t.getTrain() != null) {
                t.getTrain().setTicketID(null);
                trainService.update(t.getTrain());
            }
            try {
                ticketUp.update(t);
            } catch (Exception ignore) {
            }
        }
    }

    public List<Ticket> sortUsers(String text) {
        text = "U" + text;
        return ticketSort.sort(text);
    }

    public List<pl.lodz.p.edu.model.Tickets.Ticket> sortTrains(String text) {
        text = "T" + text;
        return ticketSort.sort(text);
    }

    @Override
    public List<Ticket> getTickets(UUID id) {
        List<Ticket> tickets = new LinkedList<>();
        for (Ticket ticket : ticketsGet.getAll()
        ) {
            if (ticket.getUser().getUserId() == id) {
                tickets.add(ticket);
            }
        }
        return tickets;
    }
}
