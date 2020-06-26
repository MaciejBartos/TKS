package pl.lodz.p.edu.model.TrainsGUI;

import pl.lodz.p.edu.model.FirmsGUI.FirmGUI;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

public class ExpressTrainGUI extends TrainGUI {

    @Range(min = 1, message = "Invalid Carriage number, must be greater than 0")
    private int carriage;

    public ExpressTrainGUI(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") FirmGUI firm, @JsonProperty("carriage") int carriage) {
        super( name, seats, firm);
        this.carriage = carriage;
    }

    public ExpressTrainGUI(){}

    public int getCarriage() {
        return carriage;
    }

    public void setCarriage(int carriage) {
        this.carriage = carriage;
    }
}
