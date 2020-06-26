package pl.lodz.p.edu.converter;

import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.model.FirmsRest.FirmRest;
import pl.lodz.p.edu.model.Trains.ExpressTrain;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.TrainsRest.ExpressTrainRest;
import pl.lodz.p.edu.model.TrainsRest.PassengerTrainRest;
import pl.lodz.p.edu.model.TrainsRest.TrainRest;

import java.util.LinkedList;
import java.util.List;

public class FromDomainConverterRest {

    public static FirmRest convertFirm(Firm firmToConvert){
        FirmRest firm = new FirmRest(firmToConvert.getName());
        firm.setFirmId(firmToConvert.getFirmId());
        return firm;
    }

    public static TrainRest convertTrain(Train trainToConvert){
        TrainRest train;
        if(trainToConvert instanceof ExpressTrain){
            train = new ExpressTrainRest();
            ((ExpressTrainRest) train).setCarriage((((ExpressTrain) trainToConvert).getCarriage()));
        }
        else {
            train = new PassengerTrainRest();
        }
        train.setFirm(FromDomainConverterRest.convertFirm(trainToConvert.getFirm()));
        train.setName(trainToConvert.getName());
        train.setSeats(trainToConvert.getSeats());
        train.setTicketID(trainToConvert.getTicketID());
        train.setTrainId(trainToConvert.getTrainId());
        return train;
    }

    public static List<TrainRest> convertTrainsList(List<Train> trainsList){
        List<TrainRest> trains = new LinkedList<>();
        for(Train train: trainsList){
            trains.add(FromDomainConverterRest.convertTrain(train));
        }
        return trains;
    }
    public static List<FirmRest> convertFirmsList(List<Firm> firmsList){
        List<FirmRest> firms = new LinkedList<>();
        for(Firm firm: firmsList){
            firms.add(FromDomainConverterRest.convertFirm(firm));
        }
        return firms;
    }
}
