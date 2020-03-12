package pl.lodz.p.edu.aggregates;

import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;
import pl.lodz.p.edu.data.TicketsEnt.TicketEnt;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;
import pl.lodz.p.edu.data.UsersEnt.UserEnt;
import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.Users.User;

public class FromDomainConverter {
    public static FirmEnt converFirm(Firm firmToConvert){
        FirmEnt firm = new FirmEnt(firmToConvert.getName());
        firm.setFirmId(firmToConvert.getFirmId());
        return firm;
    }

    public static TrainEnt convertTrain(Train trainToConvert){
        TrainEnt train = new TrainEnt();
        train.setFirm(FromDomainConverter.converFirm(trainToConvert.getFirm()));
        train.setName(trainToConvert.getName());
        train.setSeats(trainToConvert.getSeats());
        train.setTicketID(trainToConvert.getTicketID());
        train.setTrainId(trainToConvert.getTrainId());
        return train;
    }

    public static UserEnt convertUser(User userToConvert){
        UserEnt user = new UserEnt();
        user.setEmail(userToConvert.getEmail());
        user.setName(userToConvert.getName());
        user.setPassword(userToConvert.getPassword());
        user.setIsActive(userToConvert.getIsActive());
        user.setUserId(userToConvert.getUserId());
        user.setTickets(userToConvert.getTickets());
        return user;
    }

    public static TicketEnt convertTicket(Ticket ticketToConvert){
        TicketEnt ticket = new TicketEnt();
        ticket.setTicketId(ticketToConvert.getTicketId());
        ticket.setTrain(FromDomainConverter.convertTrain(ticketToConvert.getTrain()));
        ticket.setUser(FromDomainConverter.convertUser(ticketToConvert.getUser()));
        ticket.setStartingDate(ticketToConvert.getStartingDate());
        ticket.setEndingDate(ticketToConvert.getEndingDate());
        return ticket;
    }

}
