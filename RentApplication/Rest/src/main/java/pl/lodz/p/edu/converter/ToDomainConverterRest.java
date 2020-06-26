package pl.lodz.p.edu.converter;

import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.model.FirmsRest.FirmRest;
import pl.lodz.p.edu.model.Trains.ExpressTrain;
import pl.lodz.p.edu.model.Trains.PassengerTrain;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.TrainsRest.ExpressTrainRest;
import pl.lodz.p.edu.model.TrainsRest.TrainRest;

public class ToDomainConverterRest {

    public static Firm convertFirm(FirmRest firmToConvert){
        Firm firm = new Firm(firmToConvert.getName());
        firm.setFirmId(firmToConvert.getFirmId());
        return firm;
    }

    public static Train convertTrain(TrainRest trainToConvert){
        Train train;
        if(trainToConvert instanceof ExpressTrainRest){
            train = new ExpressTrain();
            ((ExpressTrain) train).setCarriage((((ExpressTrainRest) trainToConvert).getCarriage()));
        }
        else {
            train = new PassengerTrain();
        }
        train.setFirm(ToDomainConverterRest.convertFirm(trainToConvert.getFirm()));
        train.setName(trainToConvert.getName());
        train.setSeats(trainToConvert.getSeats());
        train.setTicketID(trainToConvert.getTicketID());
        train.setTrainId(trainToConvert.getTrainId());
        return train;
    }
}
