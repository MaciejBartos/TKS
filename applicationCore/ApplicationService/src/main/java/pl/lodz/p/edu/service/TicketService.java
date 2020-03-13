package pl.lodz.p.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.Users.User;
import pl.lodz.p.edu.repo.TicketRepo;
import pl.lodz.p.edu.userInterface.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService implements SaveItemUseCase<Ticket>, ShowItemUseCase<Ticket>, ShowAllItemsUseCase<Ticket>, SortTicketsByTrainUseCase, SortTicketsByUserUseCase, RemoveItemUseCase {

    //private IRepo ticketRepo;
    //private TicketRepositoryAdapter ticketRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private IAddItem<pl.lodz.p.edu.model.Tickets.Ticket> ticketAdd;
    @Autowired
    private IGetAllItems<pl.lodz.p.edu.model.Tickets.Ticket> ticketsGet;
    @Autowired
    private IGetItem<pl.lodz.p.edu.model.Tickets.Ticket> ticketGet;
    @Autowired
    private IDeleteItem<pl.lodz.p.edu.model.Tickets.Ticket> ticketDel;
    @Autowired
    private ISortItems<pl.lodz.p.edu.model.Tickets.Ticket> ticketSort;

//    @Autowired
//    public TicketService(TicketRepositoryAdapter ticketRepo, TrainService trainService, UserService userService) {
//        this.ticketRepo = ticketRepo;
//        this.userService = userService;
//        this.trainService = trainService;
//    }

    public void add(pl.lodz.p.edu.model.Tickets.Ticket t) throws Exception{
        if(trainService.get(t.getTrain().getTrainId())==null || trainService.get(t.getTrain().getTrainId()).getTicketID() != null){
            throw new Exception("train error");
        }
        t.setUser(userService.get(t.getUser().getUserId()));
        t.setTrain(trainService.get(t.getTrain().getTrainId()));
        t.getUser().getTickets().add(t.getTicketId());
        t.getTrain().setTicketID(t.getTicketId());
        //ticketRepo.add(t);
        ticketAdd.add(t);
    }

    public void delete(UUID id){
       pl.lodz.p.edu.model.Tickets.Ticket t = get(id);
        if(t.getEndingDate() == null){
            ticketDel.delete(t);
            //ticketRepo.delete(t);
            t.getTrain().setTicketID(null);
            t.getUser().getTickets().remove(t);
        }
    }

    public List<pl.lodz.p.edu.model.Tickets.Ticket> getAll(){
        return ticketsGet.getAll();
        //return ticketRepo.getAll();
    }

    public List<Train> getAllNoAllocatedTrains(){
        List<Train> t = new ArrayList<>();
        for (Train train:trainService.getAll()
             ) {
            if(train.getTicketID() == null){
                t.add(train);
            }

        }
        return t;
    }

    public List<User>getAllActiveUser(){
        List<User> users = new ArrayList<>();
        for (User user :userService.getAll()
             ) {
            if(user.getIsActive()){
                users.add(user);
            }
        }
        return users;
    }

    public pl.lodz.p.edu.model.Tickets.Ticket get(UUID id){
        Optional<pl.lodz.p.edu.model.Tickets.Ticket> ticket = ticketGet.getById(id);
//        Optional<Ticket> ticket = ticketRepo.getById(id);
        if(ticket.isPresent()){
            return ticket.get();
        }
        return new pl.lodz.p.edu.model.Tickets.Ticket();

    }

    public void endTicket(UUID id){
        pl.lodz.p.edu.model.Tickets.Ticket t = get(id);
        if(!t.getStartingDate().isAfter(LocalDate.now())){
            t.setEndingDate( LocalDate.now());
            if(t.getTrain() != null)
            t.getTrain().setTicketID(null);
        }
    }

    public List<pl.lodz.p.edu.model.Tickets.Ticket>sortUsers(String text){
        text = "U" + text;
        return ticketSort.sort(text);
//        return ((TicketRepo)ticketRepo).sortUser(text);
    }

    public List<pl.lodz.p.edu.model.Tickets.Ticket>sortTrains(String text){

        text = "T" + text;
        return ticketSort.sort(text);
        //return ((TicketRepo)ticketRepo).sortTrain(text);
    }

    public User getUserByEmail(String email){
        return userService.get(email);
    }
}
