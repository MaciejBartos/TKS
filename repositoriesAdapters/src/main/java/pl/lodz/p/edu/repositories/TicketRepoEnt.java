package pl.lodz.p.edu.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.TicketsEnt.TicketEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketRepoEnt implements IRepoEnt<TicketEnt> {

    private List<TicketEnt> tickets = new ArrayList<>();
    private TrainRepoEnt trainRepo;
    private UserRepoEnt userRepo;

    @Autowired
    public TicketRepoEnt(TrainRepoEnt trainRepo, UserRepoEnt userRepo) {
        this.trainRepo = trainRepo;
        this.userRepo = userRepo;
    }


//    @PostConstruct
//    private void postConstruct(){
//        for (int i = 0; i < 3; i++) {
//            tickets.add(new Ticket(UUID.randomUUID(),userRepo.getAll().get(i),trainRepo.getAll().get(i),LocalDate.of(1111,1,1),null));
//            tickets.get(i).getTrain().setTicket(tickets.get(i));
//            tickets.get(i).getUser().getTickets().add(tickets.get(i));
//        }
//    }


    public void add(TicketEnt t) {
        synchronized (this) {
            tickets.add(t);
        }
    }

    public Optional<TicketEnt> getById(UUID id) {
        return tickets.stream().filter(user -> user.getTicketId().equals(id)).findFirst();
    }

    public List<TicketEnt> getAll() {
        return tickets;
    }

    public void delete(TicketEnt t) {
        synchronized (this){
            tickets.remove(t);
        }
    }

    public void update(TicketEnt t) {
//        Optional<Ticket> ticket = getById(t.getTicketId());
//        if (ticket.isPresent())
//        {
//            tickets.set(tickets.indexOf(ticket), t);
//        }
    }

    public List<TicketEnt>sortUser(String text){
        List<TicketEnt> sortTickets = new ArrayList<>();
        for (TicketEnt t: tickets
        ) {
            if (t.getUser().getName().length() >= text.length()) {
                if (t.getUser().getName().substring(0, text.length()).equals(text)) {
                    sortTickets.add(t);
                }
            }

        }
        return sortTickets;
    }

    public List<TicketEnt>sortTrain(String text){
        List<TicketEnt> sortTickets = new ArrayList<>();
        for (TicketEnt t: tickets
        ) {
            if (t.getTrain().getName().length() >= text.length()) {
                if (t.getTrain().getName().substring(0, text.length()).equals(text)) {
                    sortTickets.add(t);
                }
            }

        }
        return sortTickets;
    }

}
