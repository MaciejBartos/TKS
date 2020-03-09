package pl.lodz.p.edu.data.TrainsEnt;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;
import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;

public class ExpressTrainEnt extends TrainEnt {

    public int getCarriage() {
        return carriage;
    }

    public void setCarriage(int carriage) {
        this.carriage = carriage;
    }

    @Range(min = 1, message = "Invalid Carriage number, must be greater than 0")
    private int carriage;

    public ExpressTrainEnt(@JsonProperty("name") String name, @JsonProperty("seats") int seats, @JsonProperty("firm") FirmEnt firm, @JsonProperty("carriage") int carriage) {
        super( name, seats, firm);
        this.carriage = carriage;
    }

    public ExpressTrainEnt(){}
}
