package pl.lodz.p.edu.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.userInterface.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketRestController {

    private final ShowItemUseCase<Ticket> ticketGet;
    private final ShowAllItemsUseCase<Ticket> ticketsGet;
    private final SaveItemUseCase<Ticket> ticketAdd;
    private final SortTicketsByTrainUseCase trainSort;
     private final RemoveTicketUseCase ticketDel;
     private final SortTicketsByUserClientUseCase userSort;
    private final FinishAllocationUseCase allocatedTicket;
    private final ShowAllUserClientTicketUseCase ticketsUser;

    @Autowired
    public TicketRestController(ShowItemUseCase<Ticket> ticketGet, ShowAllItemsUseCase<Ticket> ticketsGet, SaveItemUseCase<Ticket> ticketAdd, SortTicketsByTrainUseCase trainSort, RemoveTicketUseCase ticketDel, SortTicketsByUserClientUseCase userSort, FinishAllocationUseCase allocatedTicket, ShowAllUserClientTicketUseCase ticketsUser) {
        this.ticketGet = ticketGet;
        this.ticketsGet = ticketsGet;
        this.ticketAdd = ticketAdd;
        this.trainSort = trainSort;
        this.ticketDel = ticketDel;
        this.userSort = userSort;
        this.allocatedTicket = allocatedTicket;
        this.ticketsUser = ticketsUser;
    }

    @GetMapping(path = "{id}")
    public Ticket get(@PathVariable UUID id){
        return ticketGet.get(id);
    }

    @GetMapping("userTickets/{id}")
    public List<Ticket> userTickets(@PathVariable UUID id){
        return ticketsUser.getTickets(id);
    }

    @GetMapping("finish/{id}")
    public String finishTicket(UUID id){
        allocatedTicket.endTicket(id);
        return "Success";
    }

    @GetMapping("sortUser/{text}")
    public List<Ticket> userSorting(@PathVariable String text){
        return userSort.sortUsers(text);
    }

    @GetMapping
    public List<Ticket> ticketsGets() {
        return ticketsGet.getAll();
    }

    @GetMapping("sortTrain/{text}")
    public List<Ticket> trainSorting(@PathVariable String text) {
        return trainSort.sortTrains(text);
    }

    @PostMapping
    public void ticketAdds(@RequestBody Ticket ticket) {
        try {
            ticketAdd.add(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping
    public void ticketDelete(UUID id) {
        ticketDel.delete(id);
    }
}
