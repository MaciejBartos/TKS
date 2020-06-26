package pl.lodz.p.edu.data.TrainsEnt;

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

    public ExpressTrainEnt( String name, int seats,  FirmEnt firm,  int carriage) {
        super( name, seats, firm);
        this.carriage = carriage;
    }

    public ExpressTrainEnt(){}
}
