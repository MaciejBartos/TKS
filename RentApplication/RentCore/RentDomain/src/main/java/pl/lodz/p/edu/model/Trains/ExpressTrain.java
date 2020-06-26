package pl.lodz.p.edu.model.Trains;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.lodz.p.edu.model.Firms.Firm;

public class ExpressTrain extends Train {

    public int getCarriage() {
        return carriage;
    }

    public void setCarriage(int carriage) {
        this.carriage = carriage;
    }

    private int carriage;

    public ExpressTrain(String name, int seats, Firm firm, int carriage) {
        super( name, seats, firm);
        this.carriage = carriage;
    }

    public ExpressTrain(){}
}
