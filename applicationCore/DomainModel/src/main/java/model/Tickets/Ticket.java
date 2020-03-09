package pl.lodz.p.edu.model.Tickets;

import org.springframework.stereotype.Component;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.Users.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.UUID;




public class Ticket {

    private UUID ticketId;
    private User user;
    private Train train;


    private LocalDate startingDate;

    private LocalDate endingDate;

    public Ticket(UUID id, User user, Train train, LocalDate startingDate, LocalDate endingDate) {
        this.ticketId = id;
        this.user = user;
        this.train = train;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Ticket(){
        this.ticketId = UUID.randomUUID();
        //this.user = new User();
        //this.train = new Train();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }



}