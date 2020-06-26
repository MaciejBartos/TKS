package pl.lodz.p.edu.model.Trains;

import pl.lodz.p.edu.model.Firms.Firm;

import java.util.*;

public class Train {

    private UUID trainId;
    private UUID ticketID;
    private String name;
    private int seats;
    private Firm firm;

    public Train(String name, int seats, Firm firm) {
        this.trainId = UUID.randomUUID();
        this.name = name;
        this.seats = seats;
        this.firm = firm;
    }

    public Train() {
        this.trainId = UUID.randomUUID();
    }

    public UUID getTrainId() {
        return trainId;
    }

    public void setTrainId(UUID trainId) {
        this.trainId = trainId;
    }

    public UUID getTicketID() {
        return ticketID;
    }

    public void setTicketID(UUID ticket) {
        this.ticketID = ticket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int s) {
        this.seats = s;
    }

}
