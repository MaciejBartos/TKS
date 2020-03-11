package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.edu.data.TicketsEnt.TicketEnt;
import pl.lodz.p.edu.prots.*;
import pl.lodz.p.edu.repositories.IRepoEnt;
import pl.lodz.p.edu.repositories.TicketRepoEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TicketRepositoryAdapter implements IAddItem<TicketEnt>, IDeleteItem<TicketEnt>, IGetAllItems<TicketEnt>, IGetItem<TicketEnt>, ISortItems<TicketEnt> {

    @Autowired
    private IRepoEnt<TicketEnt> ticketRepo;

    @Override
    public void add(TicketEnt item) {
        ticketRepo.add(item);
    }

    @Override
    public void delete(TicketEnt item) {
        ticketRepo.delete(item);
    }

    @Override
    public List<TicketEnt> getAll() {
        return ticketRepo.getAll();
    }

    @Override
    public Optional<TicketEnt> getById(UUID id) {
        return ticketRepo.getById(id);
    }

    @Override
    public List<TicketEnt> sort(String text) {
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
