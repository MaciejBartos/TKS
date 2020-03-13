package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.TicketsEnt.TicketEnt;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.repositories.IRepoEnt;
import pl.lodz.p.edu.repositories.TicketRepoEnt;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketRepositoryAdapter implements IAddItem<Ticket>, IDeleteItem<Ticket>, IGetAllItems<Ticket>, IGetItem<Ticket>, ISortItems<Ticket> {

    @Autowired
    private IRepoEnt<TicketEnt> ticketRepo;

    @Override
    public void add(Ticket item) {
        ticketRepo.add(FromDomainConverter.convertTicket(item));
    }

    @Override
    public void delete(Ticket item) {
        ticketRepo.delete(FromDomainConverter.convertTicket(item));
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new LinkedList<>();
        for(TicketEnt ticket: ticketRepo.getAll()){
               tickets.add(ToDomainConverter.convertTicket(ticket));
        }
        return tickets;
    }

    @Override
    public Optional<Ticket> getById(UUID id) {
        return Optional.of(ToDomainConverter.convertTicket(ticketRepo.getById(id).get()));
    }

    @Override
    public List<Ticket> sort(String text) {
        if(text.charAt(0) == 'T'){
            text = text.substring(1);
            List<Ticket> tickets = new LinkedList<>();
            for(TicketEnt ticket: ((TicketRepoEnt)ticketRepo).sortTrain(text)){
                tickets.add(ToDomainConverter.convertTicket(ticket));
            }
            return tickets;
        }
        else {
            text = text.substring(1);
            List<Ticket> tickets = new LinkedList<>();
            for(TicketEnt ticket: ((TicketRepoEnt)ticketRepo).sortUser(text)){
                tickets.add(ToDomainConverter.convertTicket(ticket));
            }
            return tickets;
        }
    }
}
