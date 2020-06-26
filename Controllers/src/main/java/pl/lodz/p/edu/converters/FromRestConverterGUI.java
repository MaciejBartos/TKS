package pl.lodz.p.edu.converters;

//import pl.lodz.p.edu.model.FirmsRest.FirmRest;
//import pl.lodz.p.edu.model.FirmsGUI.FirmGUI;
//import pl.lodz.p.edu.model.TrainsGUI.ExpressTrainGUI;
//import pl.lodz.p.edu.model.TrainsGUI.PassengerTrainGUI;
//import pl.lodz.p.edu.model.TrainsRest.ExpressTrainRest;
//import pl.lodz.p.edu.model.TrainsRest.TrainRest;
//import pl.lodz.p.edu.model.TrainsGUI.TrainGUI;

import java.util.LinkedList;
import java.util.List;

//public class FromRestConverterGUI {

//    public static FirmGUI convertFirm(FirmRest firmToConvert){
//        FirmGUI firm = new FirmGUI(firmToConvert.getName());
//        firm.setFirmId(firmToConvert.getFirmId());
//        return firm;
//    }
//
//    public static TrainGUI convertTrain(TrainRest trainToConvert){
//        TrainGUI train;
//        if(trainToConvert instanceof ExpressTrainRest){
//            train = new ExpressTrainGUI();
//            ((ExpressTrainGUI) train).setCarriage((((ExpressTrainRest) trainToConvert).getCarriage()));
//        }
//        else {
//            train = new PassengerTrainGUI();
//        }
//        train.setFirm(FromRestConverterGUI.convertFirm(trainToConvert.getFirm()));
//        train.setName(trainToConvert.getName());
//        train.setSeats(trainToConvert.getSeats());
//        train.setTicketID(trainToConvert.getTicketID());
//        train.setTrainId(trainToConvert.getTrainId());
//        return train;
//    }
//
//    public static List<TrainGUI> convertTrainsList(List<TrainRest> trainsList){
//        List<TrainGUI> trains = new LinkedList<>();
//        for(TrainRest train: trainsList){
//            trains.add(FromRestConverterGUI.convertTrain(train));
//        }
//        return trains;
//    }
//    public static List<FirmGUI> convertFirmsList(List<FirmRest> firmsList){
//        List<FirmGUI> firms = new LinkedList<>();
//        for(FirmRest firm: firmsList){
//            firms.add(FromRestConverterGUI.convertFirm(firm));
//        }
//        return firms;
//    }
//}
