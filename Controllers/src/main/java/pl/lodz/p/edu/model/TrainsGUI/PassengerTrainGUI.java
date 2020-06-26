package pl.lodz.p.edu.model.TrainsGUI;

import pl.lodz.p.edu.model.FirmsGUI.FirmGUI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PassengerTrainGUI extends TrainGUI {

    public PassengerTrainGUI(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm")
            FirmGUI firm) {
        super(name, seats, firm);
    }

    public PassengerTrainGUI() {
    }
}
