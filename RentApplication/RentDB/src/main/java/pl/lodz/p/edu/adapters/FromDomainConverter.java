package pl.lodz.p.edu.adapters;

import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;
import pl.lodz.p.edu.data.TicketsEnt.TicketEnt;
import pl.lodz.p.edu.data.TrainsEnt.ExpressTrainEnt;
import pl.lodz.p.edu.data.TrainsEnt.PassengerTrainEnt;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;
import pl.lodz.p.edu.model.Clients.UserClient;
import pl.lodz.p.edu.data.UsersEnt.UserClientEnt;
import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.model.Trains.ExpressTrain;
import pl.lodz.p.edu.model.Trains.Train;

public class FromDomainConverter {
    public static FirmEnt convertFirm(Firm firmToConvert){
        FirmEnt firm = new FirmEnt(firmToConvert.getName());
        firm.setFirmId(firmToConvert.getFirmId());
        return firm;
    }

    public static TrainEnt convertTrain(Train trainToConvert){
       TrainEnt train;
        if(trainToConvert instanceof ExpressTrain){
            train = new ExpressTrainEnt();
            ((ExpressTrainEnt) train).setCarriage((((ExpressTrain) trainToConvert).getCarriage()));
        }
        else {
             train = new PassengerTrainEnt();
        }
        train.setFirm(FromDomainConverter.convertFirm(trainToConvert.getFirm()));
        train.setName(trainToConvert.getName());
        train.setSeats(trainToConvert.getSeats());
        train.setTicketID(trainToConvert.getTicketID());
        train.setTrainId(trainToConvert.getTrainId());
        return train;
    }

    public static UserClientEnt convertUser(UserClient userToConvert){
        UserClientEnt user = new UserClientEnt();
        user.setEmail(userToConvert.getEmail());
        user.setName(userToConvert.getName());
        //user.setPassword(userToConvert.getPassword());
        user.setIsActive(userToConvert.getIsActive());
        user.setUserId(userToConvert.getUserId());
        //user.setTickets(userToConvert.getTickets());
        user.setType(userToConvert.getType());
        return user;
    }

    public static TicketEnt convertTicket(Ticket ticketToConvert){
        TicketEnt ticket = new TicketEnt();
        ticket.setTicketId(ticketToConvert.getTicketId());
        ticket.setTrain(FromDomainConverter.convertTrain(ticketToConvert.getTrain()));
        ticket.setUser(FromDomainConverter.convertUser(ticketToConvert.getUser()));
        ticket.setStartingLocalDate(ticketToConvert.getStartingDate());
        ticket.setEndingLocalDate(ticketToConvert.getEndingDate());
        return ticket;
    }

}
