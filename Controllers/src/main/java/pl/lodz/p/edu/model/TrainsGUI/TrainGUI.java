package pl.lodz.p.edu.model.TrainsGUI;

import pl.lodz.p.edu.model.FirmsGUI.FirmGUI;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

public class TrainGUI {

    private UUID trainId;
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must have between 2-20 letters")
    private String name;
    @Range(min = 1, message = "Invalid Seats number, must be greater than 0")
    private int seats;
    private FirmGUI firm;
    private UUID ticketID;

    public TrainGUI(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") FirmGUI firm) {
        this.trainId = UUID.randomUUID();
        this.name = name;
        this.seats = seats;
        this.firm = firm;
    }

    public TrainGUI() {
        this.trainId = UUID.randomUUID();
    }

    public UUID getTicketID() {
        return ticketID;
    }

    public void setTicketID(UUID ticket) {
        this.ticketID = ticket;
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

    public FirmGUI getFirm() {
        return firm;
    }

    public void setFirm(FirmGUI firm) {
        this.firm = firm;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int s) {
        this.seats = s;
    }
}
