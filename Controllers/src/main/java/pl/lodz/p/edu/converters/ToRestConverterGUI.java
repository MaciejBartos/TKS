//package pl.lodz.p.edu.converters;
//
//
//import pl.lodz.p.edu.model.FirmsRest.FirmRest;
//import pl.lodz.p.edu.model.FirmsGUI.FirmGUI;
//
//import pl.lodz.p.edu.model.TrainsRest.ExpressTrainRest;
//import pl.lodz.p.edu.model.TrainsRest.PassengerTrainRest;
//import pl.lodz.p.edu.model.TrainsRest.TrainRest;
//import pl.lodz.p.edu.model.TrainsGUI.ExpressTrainGUI;
//
//import pl.lodz.p.edu.model.TrainsGUI.TrainGUI;
//
//public class ToRestConverterGUI {
//
//    public static FirmRest convertFirm(FirmGUI firmToConvert){
//        FirmRest firm = new FirmRest(firmToConvert.getName());
//        firm.setFirmId(firmToConvert.getFirmId());
//        return firm;
//    }
//
//    public static TrainRest convertTrain(TrainGUI trainToConvert){
//        TrainRest train;
//        if(trainToConvert instanceof ExpressTrainGUI){
//            train = new ExpressTrainRest();
//            ((ExpressTrainRest) train).setCarriage((((ExpressTrainGUI) trainToConvert).getCarriage()));
//        }
//        else {
//            train = new PassengerTrainRest();
//        }
//        train.setFirm(ToRestConverterGUI.convertFirm(trainToConvert.getFirm()));
//        train.setName(trainToConvert.getName());
//        train.setSeats(trainToConvert.getSeats());
//        train.setTicketID(trainToConvert.getTicketID());
//        train.setTrainId(trainToConvert.getTrainId());
//        return train;
//    }
//}
