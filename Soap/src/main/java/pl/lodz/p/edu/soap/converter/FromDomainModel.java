package pl.lodz.p.edu.soap.converter;

//import pl.lodz.p.edu.model.Firms.Firm;
//import pl.lodz.p.edu.model.Tickets.Ticket;
//import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.Users.User;
import pl.lodz.p.edu.soap.model.FirmDTO;
import pl.lodz.p.edu.soap.model.TicketDTO;
import pl.lodz.p.edu.soap.model.TrainDTO;
import pl.lodz.p.edu.soap.model.UserDTO;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class FromDomainModel {

    public static UserDTO fromUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId().toString());
        userDTO.setType(user.getType());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setActive(user.getIsActive());

        return userDTO;
    }
//
//    public static TicketDTO fromTicketToTicketDTO(Ticket ticket) {
//
//        TicketDTO ticketDTO = new TicketDTO();
//
//        ticketDTO.setTicketId(ticket.getTicketId().toString());
//        ticketDTO.setUser(fromUserToUserDTO(ticket.getUser()));
//        ticketDTO.setTrain(fromTrainToTrainDTO(ticket.getTrain()));
//        try {
//            System.out.println(ticket.getStartingDate().toString());
//            ticketDTO.setStartingDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(ticket.getStartingDate().toString()));
//            if (!(ticket.getEndingDate() == null)) {
//                ticketDTO.setEndingDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(ticket.getEndingDate().toString()));
//            }
//        } catch (DatatypeConfigurationException e) {
//            e.printStackTrace();
//        }
//
//        return ticketDTO;
//    }
//
//    public static FirmDTO fromFirmToFirmDTO(Firm firm) {
//
//        FirmDTO firmDTO = new FirmDTO();
//
//        firmDTO.setFirmId(firm.getFirmId().toString());
//        firmDTO.setName(firm.getName());
//
//        return firmDTO;
//    }
//
//    public static TrainDTO fromTrainToTrainDTO(Train train) {
//
//        TrainDTO trainDTO = new TrainDTO();
//
//        trainDTO.setFirm(fromFirmToFirmDTO(train.getFirm()));
//        trainDTO.setName(train.getName());
//        trainDTO.setSeats(train.getSeats());
//        trainDTO.setTicketID(train.getTicketID().toString());
//        trainDTO.setTrainId(train.getTrainId().toString());
//
//        return trainDTO;
//    }
}
