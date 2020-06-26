package pl.lodz.p.edu.model.TrainsRest;


import com.fasterxml.jackson.annotation.JsonProperty;
import pl.lodz.p.edu.model.FirmsRest.FirmRest;

public class PassengerTrainRest extends TrainRest {

    public PassengerTrainRest(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm")
            FirmRest firm) {
        super(name, seats, firm);
    }

    public PassengerTrainRest() {
    }
}
