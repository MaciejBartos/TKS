package pl.lodz.p.edu.model.TicketsGUI;


import org.springframework.format.annotation.DateTimeFormat;
import pl.lodz.p.edu.model.TrainsGUI.TrainGUI;
import pl.lodz.p.edu.model.UsersGUI.UserGUI;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class TicketGUI {

    private UUID ticketId;
    private UserGUI user;
    private TrainGUI train;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date cant be empty")
    private LocalDate startingDate;

    private LocalDate endingDate;

    public TicketGUI(UUID id, UserGUI user, TrainGUI train, LocalDate startingDate, LocalDate endingDate) {
        this.ticketId = id;
        this.user = user;
        this.train = train;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public TicketGUI(){
        this.ticketId = UUID.randomUUID();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public UserGUI getUser() {
        return user;
    }

    public void setUser(UserGUI user) {
        this.user = user;
    }

    public TrainGUI getTrain() {
        return train;
    }

    public void setTrain(TrainGUI train) {
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
