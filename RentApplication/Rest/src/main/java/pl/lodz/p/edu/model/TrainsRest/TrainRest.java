package pl.lodz.p.edu.model.TrainsRest;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;
import pl.lodz.p.edu.model.FirmsRest.FirmRest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

public class TrainRest {


    private UUID trainId;
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must have between 2-20 letters")
    private String name;
    @Range(min = 1, message = "Invalid Seats number, must be greater than 0")
    private int seats;
    private FirmRest firm;

    public UUID getTicketID() {
        return ticketID;
    }

    public void setTicketID(UUID ticket) {
        this.ticketID = ticket;
    }

    //private TicketEnt ticket;
    private UUID ticketID;

    public TrainRest(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") FirmRest firm) {
        this.trainId = UUID.randomUUID();
        this.name = name;
        this.seats = seats;
        this.firm = firm;
    }

    public TrainRest() {
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

    public FirmRest getFirm() {
        return firm;
    }
//public String getFirm() {
//    return firm.getName();
//}

    public void setFirm(FirmRest firm) {
        this.firm = firm;
    }
//public void setFirm(String name) {
//    this.firm.setName(name);
//}

    public int getSeats() {
//        return seats.size();
        return seats;
    }

    public void setSeats(int s) {
//        List<Seat> seats1 = new ArrayList<>();
//        for (int i = 0; i < s; i++) {
//            seats1.add(new Seat(Integer.toString(i+1)));
//        }
//        seats = seats1;
        this.seats = s;
    }

//    public List<Seat> getSeats() {
//        return new ArrayList<>(seats);
//    }

//    public Seat getSeatById(UUID trainId) {
//        return seats.
//    }
//
//    public void setSeats(List<Boolean> seats) {
//        this.seats = seats;
//    }


}
