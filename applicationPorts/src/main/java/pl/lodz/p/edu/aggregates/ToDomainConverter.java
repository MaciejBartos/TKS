package pl.lodz.p.edu.aggregates;

import pl.lodz.p.edu.data.FirmsEnt.FirmEnt;
import pl.lodz.p.edu.data.TicketsEnt.TicketEnt;
import pl.lodz.p.edu.data.TrainsEnt.TrainEnt;
import pl.lodz.p.edu.data.UsersEnt.UserEnt;
import pl.lodz.p.edu.model.Firms.Firm;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.Users.User;

public class ToDomainConverter {

    public static Firm converFirm(FirmEnt firmToConvert){
        Firm firm = new Firm(firmToConvert.getName());
        firm.setFirmId(firmToConvert.getFirmId());
        return firm;
    }

    public static Train convertTrain(TrainEnt trainToConvert){
        Train train = new Train();
        train.setFirm(ToDomainConverter.converFirm(trainToConvert.getFirm()));
        train.setName(trainToConvert.getName());
        train.setSeats(trainToConvert.getSeats());
        train.setTicketID(trainToConvert.getTicketID());
        train.setTrainId(trainToConvert.getTrainId());
        return train;
    }

    public static User convertUser(UserEnt userToConvert){
        User user = new User();
        user.setEmail(userToConvert.getEmail());
        user.setName(userToConvert.getName());
        user.setPassword(userToConvert.getPassword());
        user.setIsActive(userToConvert.getIsActive());
        user.setUserId(userToConvert.getUserId());
        user.setTickets(userToConvert.getTickets());
        return user;
    }

    public static Ticket convertTicket(TicketEnt ticketToConvert){
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketToConvert.getTicketId());
        ticket.setTrain(ToDomainConverter.convertTrain(ticketToConvert.getTrain()));
        ticket.setUser(ToDomainConverter.convertUser(ticketToConvert.getUser()));
        ticket.setStartingDate(ticketToConvert.getStartingDate());
        ticket.setEndingDate(ticketToConvert.getEndingDate());
        return ticket;
    }
}
