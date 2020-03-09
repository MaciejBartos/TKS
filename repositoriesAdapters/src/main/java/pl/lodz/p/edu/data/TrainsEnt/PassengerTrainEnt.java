package pl.lodz.p.edu.data.TrainsEnt;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;

public class PassengerTrainEnt extends TrainEnt {

    public PassengerTrainEnt(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") FirmEnt firm) {
        super( name, seats, firm);
    }

    public PassengerTrainEnt(){}
}
