package pl.lodz.p.edu.soap.model;


public class TrainDTO {
    private String trainId;
    private String ticketID;
    private String name;
    private int seats;
    private FirmDTO firm;

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public FirmDTO getFirm() {
        return firm;
    }

    public void setFirm(FirmDTO firm) {
        this.firm = firm;
    }
}
