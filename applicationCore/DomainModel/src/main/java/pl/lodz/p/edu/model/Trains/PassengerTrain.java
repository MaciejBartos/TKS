package pl.lodz.p.edu.model.Trains;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.lodz.p.edu.model.Firms.Firm;

public class PassengerTrain extends Train{

    public PassengerTrain(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") Firm firm) {
        super( name, seats, firm);
    }

    public PassengerTrain(){}
}
