package pl.lodz.p.edu.data.TicketsEnt;


import org.springframework.format.annotation.DateTimeFormat;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;
import pl.lodz.p.edu.data.UsersEnt.UserClientEnt;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class TicketEnt {

    private UUID ticketId;
    private UserClientEnt user;
    private TrainEnt train;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date cant be empty")
    private LocalDate startingDate;

    private LocalDate endingDate;

    public TicketEnt(UUID id, UserClientEnt user, TrainEnt train, LocalDate startingDate, LocalDate endingDate) {
        this.ticketId = id;
        this.user = user;
        this.train = train;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public TicketEnt(){
        this.ticketId = UUID.randomUUID();
        //this.user = new UserClientEnt();
        //this.train = new TrainEnt();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public UserClientEnt getUser() {
        return user;
    }

    public void setUser(UserClientEnt user) {
        this.user = user;
    }

    public TrainEnt getTrain() {
        return train;
    }

    public void setTrain(TrainEnt train) {
        this.train = train;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = LocalDate.parse(startingDate, formatter);
    }


    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingLocalDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = LocalDate.parse(endingDate, formatter);
    }

    public void setEndingLocalDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }



}
