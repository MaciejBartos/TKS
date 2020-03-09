package pl.lodz.p.edu.model.Trains;

import pl.lodz.p.edu.model.Firms.Firm;

import java.util.UUID;

public class PassengerTrain extends Train{

    public PassengerTrain(UUID id, String name, int numberOfSeats, Firm firm) {
        super(id, name, numberOfSeats, firm);
    }

    public PassengerTrain(){}
}
