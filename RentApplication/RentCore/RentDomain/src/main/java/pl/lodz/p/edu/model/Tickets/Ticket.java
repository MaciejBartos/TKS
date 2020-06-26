package pl.lodz.p.edu.model.Tickets;



import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.Clients.UserClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;




public class Ticket {

    private UUID ticketId;
    private UserClient user;
    private Train train;
    private LocalDate startingDate;
    private LocalDate endingDate;

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

    public Ticket(UUID id, UserClient user, Train train, LocalDate startingDate, LocalDate endingDate) {
        this.ticketId = id;
        this.user = user;
        this.train = train;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public Ticket(){
        this.ticketId = UUID.randomUUID();
        //this.user = new UserClient();
        //this.train = new Train();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public UserClient getUser() {
        return user;
    }

    public void setUser(UserClient user) {
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

    public void setStartingDate(String startingDate) {
        this.startingDate = LocalDate.parse(startingDate, formatter);
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
