package pl.lodz.p.edu.adapters;

import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;
import pl.lodz.p.edu.data.TicketsEnt.TicketEnt;
import pl.lodz.p.edu.data.TrainsEnt.ExpressTrainEnt;
import pl.lodz.p.edu.data.TrainsEnt.PassengerTrainEnt;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;
import pl.lodz.p.edu.data.UsersEnt.UserClientEnt;
import pl.lodz.p.edu.model.Clients.UserClient;
import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.model.Trains.ExpressTrain;
import pl.lodz.p.edu.model.Trains.PassengerTrain;
import pl.lodz.p.edu.model.Trains.Train;

public class ToDomainConverter {

    public static Firm convertFirm(FirmEnt firmToConvert){
        Firm firm = new Firm(firmToConvert.getName());
        firm.setFirmId(firmToConvert.getFirmId());
        return firm;
    }

    public static Train convertTrain(TrainEnt trainToConvert){
        Train train;
        if(trainToConvert instanceof ExpressTrainEnt){
            train = new ExpressTrain();
            ((ExpressTrain) train).setCarriage((((ExpressTrainEnt) trainToConvert).getCarriage()));
        }
        else {
            train = new PassengerTrain();
        }
        train.setFirm(ToDomainConverter.convertFirm(trainToConvert.getFirm()));
        train.setName(trainToConvert.getName());
        train.setSeats(trainToConvert.getSeats());
        train.setTicketID(trainToConvert.getTicketID());
        train.setTrainId(trainToConvert.getTrainId());
        return train;
    }

    public static UserClient convertUser(UserClientEnt userToConvert){
        UserClient user = new UserClient();
        user.setEmail(userToConvert.getEmail());
        user.setName(userToConvert.getName());
        //user.setPassword(userToConvert.getPassword());
        user.setIsActive(userToConvert.getIsActive());
        user.setUserId(userToConvert.getUserId());
       // user.setTickets(userToConvert.getTickets());
        user.setType(userToConvert.getType());
        return user;
    }

    public static Ticket convertTicket(TicketEnt ticketToConvert){
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketToConvert.getTicketId());
        ticket.setTrain(ToDomainConverter.convertTrain(ticketToConvert.getTrain()));
        ticket.setUser(ToDomainConverter.convertUser(ticketToConvert.getUser()));
        ticket.setStartingLocalDate(ticketToConvert.getStartingDate());
        ticket.setEndingLocalDate(ticketToConvert.getEndingDate());
        return ticket;
    }
}
