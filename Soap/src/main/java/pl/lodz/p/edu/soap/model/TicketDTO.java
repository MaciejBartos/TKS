package pl.lodz.p.edu.soap.model;


import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

public class TicketDTO {

    private String ticketId;
    private UserDTO user;
    private TrainDTO train;
    private XMLGregorianCalendar startingDate;
    private XMLGregorianCalendar endingDate;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TrainDTO getTrain() {
        return train;
    }

    public void setTrain(TrainDTO train) {
        this.train = train;
    }

    public XMLGregorianCalendar getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(XMLGregorianCalendar startingDate) {
        this.startingDate = startingDate;
    }

    public XMLGregorianCalendar getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(XMLGregorianCalendar endingDate) {
        this.endingDate = endingDate;
    }
}
