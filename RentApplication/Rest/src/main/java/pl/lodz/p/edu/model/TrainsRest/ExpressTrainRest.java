package pl.lodz.p.edu.model.TrainsRest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;
import pl.lodz.p.edu.model.FirmsRest.FirmRest;

public class ExpressTrainRest extends TrainRest {

    public int getCarriage() {
        return carriage;
    }

    public void setCarriage(int carriage) {
        this.carriage = carriage;
    }

    @Range(min = 1, message = "Invalid Carriage number, must be greater than 0")
    private int carriage;

    public ExpressTrainRest(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") FirmRest firm, @JsonProperty("carriage") int carriage) {
        super( name, seats, firm);
        this.carriage = carriage;
    }

    public ExpressTrainRest(){}
}
