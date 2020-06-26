//package pl.lodz.p.edu.converters;
//
//import pl.lodz.p.edu.model.Clients.UserClient;
//import pl.lodz.p.edu.model.FirmsGUI.FirmGUI;
//import pl.lodz.p.edu.model.TicketsGUI.TicketGUI;
//import pl.lodz.p.edu.model.TrainsGUI.ExpressTrainGUI;
//import pl.lodz.p.edu.model.TrainsGUI.TrainGUI;
//import pl.lodz.p.edu.model.UsersGUI.UserGUI;
//import pl.lodz.p.edu.model.Firms.Firm;
//import pl.lodz.p.edu.model.Tickets.Ticket;
//import pl.lodz.p.edu.model.Trains.ExpressTrain;
//import pl.lodz.p.edu.model.Trains.PassengerTrain;
//import pl.lodz.p.edu.model.Trains.Train;
//import pl.lodz.p.edu.model.Users.User;
//
//public class ToUserDomainConverterGUI {
//
//    public static Firm convertFirm(FirmGUI firmToConvert){
//        Firm firm = new Firm(firmToConvert.getName());
//        firm.setFirmId(firmToConvert.getFirmId());
//        return firm;
//    }
//
//    public static Train convertTrain(TrainGUI trainToConvert){
//        Train train;
//        if(trainToConvert instanceof ExpressTrainGUI){
//            train = new ExpressTrain();
//            ((ExpressTrain) train).setCarriage((((ExpressTrainGUI) trainToConvert).getCarriage()));
//        }
//        else {
//            train = new PassengerTrain();
//        }
//        train.setFirm(ToUserDomainConverterGUI.convertFirm(trainToConvert.getFirm()));
//        train.setName(trainToConvert.getName());
//        train.setSeats(trainToConvert.getSeats());
//        train.setTicketID(trainToConvert.getTicketID());
//        train.setTrainId(trainToConvert.getTrainId());
//        return train;
//    }
//
////    public static User convertUser(UserGUI userToConvert){
////        User user = new User();
////        user.setEmail(userToConvert.getEmail());
////        user.setName(userToConvert.getName());
////        user.setPassword(userToConvert.getPassword());
////        user.setIsActive(userToConvert.getIsActive());
////        user.setUserId(userToConvert.getUserId());
////        user.setType(userToConvert.getType());
////        return user;
////    }
//
//    public static UserClient convertUserClient(UserGUI userToConvert){
//        UserClient user = new UserClient();
//        user.setEmail(userToConvert.getEmail());
//        user.setName(userToConvert.getName());
//        //user.setPassword(userToConvert.getPassword());
//        user.setIsActive(userToConvert.getIsActive());
//        user.setUserId(userToConvert.getUserId());
//        user.setType(userToConvert.getType());
//        return user;
//    }
//
//    public static Ticket convertTicket(TicketGUI ticketToConvert){
//        Ticket ticket = new Ticket();
//        ticket.setTicketId(ticketToConvert.getTicketId());
//        ticket.setTrain(ToUserDomainConverterGUI.convertTrain(ticketToConvert.getTrain()));
//        ticket.setUser(ToUserDomainConverterGUI.convertUserClient(ticketToConvert.getUser()));
//        ticket.setStartingLocalDate(ticketToConvert.getStartingDate());
//        ticket.setEndingLocalDate(ticketToConvert.getEndingDate());
//        return ticket;
//    }
//}
