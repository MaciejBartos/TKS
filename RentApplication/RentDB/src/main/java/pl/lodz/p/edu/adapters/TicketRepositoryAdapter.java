package pl.lodz.p.edu.adapters;

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
public class TicketRepositoryAdapter implements IUpdateItem<Ticket>,IAddItem<Ticket>, IDeleteItem<Ticket>, IGetAllItems<Ticket>, IGetItem<Ticket>, ISortItems<Ticket> {

    @Autowired
    private IRepoEnt<TicketEnt> ticketRepo;

    @Override
    public void add(Ticket item) {
        try {
            ticketRepo.add(FromDomainConverter.convertTicket(item));
        } catch (Exception ignore) {
        }
    }

    @Override
    public void delete(Ticket item) {
        try {
            ticketRepo.delete(FromDomainConverter.convertTicket(item));
        } catch (Exception ignore) {
        }
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
        Optional<TicketEnt> ticket = ticketRepo.getById(id);
        if(ticket.isPresent()){
            return Optional.of(ToDomainConverter.convertTicket(ticket.get()));
        }
        return null;
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

    @Override
    public void update(Ticket item) {
        try {
            ticketRepo.update(FromDomainConverter.convertTicket(item));
        } catch (Exception ignore) {
        }
    }
}
