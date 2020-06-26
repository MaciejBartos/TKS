package pl.lodz.p.edu.converters;

//import pl.lodz.p.edu.model.FirmsGUI.FirmGUI;
//import pl.lodz.p.edu.model.TicketsGUI.TicketGUI;
//import pl.lodz.p.edu.model.TrainsGUI.ExpressTrainGUI;
//import pl.lodz.p.edu.model.TrainsGUI.PassengerTrainGUI;
//import pl.lodz.p.edu.model.TrainsGUI.TrainGUI;
//import pl.lodz.p.edu.model.UsersGUI.UserGUI;
//import pl.lodz.p.edu.model.Firms.Firm;
//import pl.lodz.p.edu.model.Tickets.Ticket;
//import pl.lodz.p.edu.model.Trains.ExpressTrain;
//import pl.lodz.p.edu.model.Trains.Train;
//import pl.lodz.p.edu.model.Users.User;
//import pl.lodz.p.edu.model.Clients.UserClient;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class FromUserDomainConverterGUI {
//
//    public static FirmGUI convertFirm(Firm firmToConvert){
//        FirmGUI firm = new FirmGUI(firmToConvert.getName());
//        firm.setFirmId(firmToConvert.getFirmId());
//        return firm;
//    }
//
//    public static TrainGUI convertTrain(Train trainToConvert){
//       TrainGUI train;
//        if(trainToConvert instanceof ExpressTrain){
//            train = new ExpressTrainGUI();
//            ((ExpressTrainGUI) train).setCarriage((((ExpressTrain) trainToConvert).getCarriage()));
//        }
//        else {
//             train = new PassengerTrainGUI();
//        }
//        train.setFirm(FromUserDomainConverterGUI.convertFirm(trainToConvert.getFirm()));
//        train.setName(trainToConvert.getName());
//        train.setSeats(trainToConvert.getSeats());
//        train.setTicketID(trainToConvert.getTicketID());
//        train.setTrainId(trainToConvert.getTrainId());
//        return train;
//    }
//
////    public static UserGUI convertUser(User userToConvert){
////        UserGUI user = new UserGUI();
////        user.setEmail(userToConvert.getEmail());
////        user.setName(userToConvert.getName());
////        user.setPassword(userToConvert.getPassword());
////        user.setIsActive(userToConvert.getIsActive());
////        user.setUserId(userToConvert.getUserId());
////        user.setType(userToConvert.getType());
////        return user;
////    }
//    public static UserGUI convertUserClient(UserClient userToConvert){
//        UserGUI user = new UserGUI();
//        user.setEmail(userToConvert.getEmail());
//        user.setName(userToConvert.getName());
//       // user.setPassword(userToConvert.getPassword());
//        user.setIsActive(userToConvert.getIsActive());
//        user.setUserId(userToConvert.getUserId());
//        user.setType(userToConvert.getType());
//        return user;
//    }
//
//    public static TicketGUI convertTicket(Ticket ticketToConvert){
//        TicketGUI ticket = new TicketGUI();
//        ticket.setTicketId(ticketToConvert.getTicketId());
//        ticket.setTrain(FromUserDomainConverterGUI.convertTrain(ticketToConvert.getTrain()));
//        ticket.setUser(FromUserDomainConverterGUI.convertUserClient(ticketToConvert.getUser()));
//        ticket.setStartingLocalDate(ticketToConvert.getStartingDate());
//        ticket.setEndingLocalDate(ticketToConvert.getEndingDate());
//        return ticket;
//    }
//
////    public static List<UserGUI> convertUsersList(List<User> usersList){
////        List<UserGUI> users = new LinkedList<>();
////        for(User user: usersList){
////            users.add(FromUserDomainConverterGUI.convertUser(user));
////        }
////        return users;
////    }
//
//    public static List<UserGUI> convertUsersClientList(List<UserClient> usersList){
//        List<UserGUI> users = new LinkedList<>();
//        for(UserClient user: usersList){
//            users.add(FromUserDomainConverterGUI.convertUserClient(user));
//        }
//        return users;
//    }
//
//    public static List<TicketGUI> convertTicketsList(List<Ticket> ticketsList){
//        List<TicketGUI> tickets = new LinkedList<>();
//        for(Ticket ticket: ticketsList){
//            tickets.add(FromUserDomainConverterGUI.convertTicket(ticket));
//        }
//        return tickets;
//    }
//    public static List<TrainGUI> convertTrainsList(List<Train> trainsList){
//        List<TrainGUI> trains = new LinkedList<>();
//        for(Train train: trainsList){
//            trains.add(FromUserDomainConverterGUI.convertTrain(train));
//        }
//        return trains;
//    }
//    public static List<FirmGUI> convertFirmsList(List<Firm> firmsList){
//        List<FirmGUI> firms = new LinkedList<>();
//        for(Firm firm: firmsList){
//            firms.add(FromUserDomainConverterGUI.convertFirm(firm));
//        }
//        return firms;
//    }
//
//}
