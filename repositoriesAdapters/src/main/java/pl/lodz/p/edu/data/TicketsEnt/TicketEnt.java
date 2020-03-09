package pl.lodz.p.edu.data.TicketsEnt;

import org.springframework.format.annotation.DateTimeFormat;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;
import pl.lodz.p.edu.data.UsersEnt.UserEnt;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;


public class TicketEnt {

    private UUID ticketId;
    private UserEnt user;
    private TrainEnt train;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date cant be empty")
    private LocalDate startingDate;

    private LocalDate endingDate;

    public TicketEnt(UUID id, UserEnt user, TrainEnt train, LocalDate startingDate, LocalDate endingDate) {
        this.ticketId = id;
        this.user = user;
        this.train = train;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public TicketEnt(){
        this.ticketId = UUID.randomUUID();
        //this.user = new UserEnt();
        //this.train = new TrainEnt();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public UserEnt getUser() {
        return user;
    }

    public void setUser(UserEnt user) {
        this.user = user;
    }

    public TrainEnt getTrain() {
        return train;
    }

    public void setTrain(TrainEnt train) {
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
