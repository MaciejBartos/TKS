package pl.lodz.p.edu.data.TrainsEnt;


import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;

public class PassengerTrainEnt extends TrainEnt {

    public PassengerTrainEnt(String name, int seats, FirmEnt firm) {
        super(name, seats, firm);
    }

    public PassengerTrainEnt() {
    }
}
