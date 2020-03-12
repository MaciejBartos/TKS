package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;

import pl.lodz.p.edu.data.TicketsEnt.TicketEnt;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.repositories.IRepoEnt;
import pl.lodz.p.edu.repositories.TicketRepoEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TicketRepositoryAdapter implements IAddItem<Ticket>, IDeleteItem<Ticket>, IGetAllItems<Ticket>, IGetItem<Ticket>, ISortItems<Ticket> {

    @Autowired
    private IRepoEnt<TicketEnt> ticketRepo;

    @Override
    public void add(Ticket item) {
        ticketRepo.add(item);
    }

    @Override
    public void delete(Ticket item) {
        ticketRepo.delete(item);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepo.getAll();
    }

    @Override
    public Optional<Ticket> getById(UUID id) {
        return ticketRepo.getById(id);
    }

    @Override
    public List<Ticket> sort(String text) {
        if(text.charAt(0) == 'T'){
            text = text.substring(1);
            return ((TicketRepoEnt)ticketRepo).sortTrain(text);
        }
        else {
            text = text.substring(1);
            return ((TicketRepoEnt)ticketRepo).sortUser(text);
        }
    }
}
