package pl.lodz.p.edu.data.TrainsEnt;


import org.hibernate.validator.constraints.Range;
import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

public class TrainEnt {


    private UUID trainId;
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must have between 2-20 letters")
    private String name;
    @Range(min = 1, message = "Invalid Seats number, must be greater than 0")
    private int seats;
    private FirmEnt firm;

    public UUID getTicketID() {
        return ticketID;
    }

    public void setTicketID(UUID ticket) {
        this.ticketID = ticket;
    }

    //private TicketEnt ticket;
    private UUID ticketID;

    public TrainEnt(String name, int seats, FirmEnt firm) {
        this.trainId = UUID.randomUUID();
        this.name = name;
        this.seats = seats;
        this.firm = firm;
    }

    public TrainEnt() {
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

    public FirmEnt getFirm() {
        return firm;
    }
//public String getFirm() {
//    return firm.getName();
//}

    public void setFirm(FirmEnt firm) {
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
