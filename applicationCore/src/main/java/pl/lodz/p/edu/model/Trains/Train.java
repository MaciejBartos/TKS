package pl.lodz.p.edu.model.Trains;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.lodz.p.edu.model.Firms.Firm;


import java.util.*;

public class Train {


    private UUID trainId;

    private String name;

    private int seats;
    private Firm firm;

    public UUID getTicketID() {
        return ticketID;
    }

    public void setTicketID(UUID ticket) {
        this.ticketID = ticket;
    }

    //private Ticket ticket;
    private UUID ticketID;

    public Train (@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") Firm firm) {
        this.trainId = UUID.randomUUID();
        this.name = name;
        this.seats = seats;
        this.firm = firm;
    }

    public Train(){
        this.trainId = UUID.randomUUID();
//        seats = new ArrayList<>();
    }

    public UUID getTrainId() {
        return trainId;
    }

    public void setTrainId(UUID trainId) {
        this.trainId = trainId;
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
//public String getFirm() {
//    return firm.getName();
//}

    public void setFirm(Firm firm) {
        this.firm = firm;
    }
//public void setFirm(String name) {
//    this.firm.setName(name);
//}

    public int getSeats(){
//        return seats.size();
        return seats;
    }

    public void setSeats(int s){
//        List<Seat> seats1 = new ArrayList<>();
//        for (int i = 0; i < s; i++) {
//            seats1.add(new Seat(Integer.toString(i+1)));
//        }
//        seats = seats1;
        this.seats = s;
    }




}
