package pl.lodz.p.edu.soap.services;

//import pl.lodz.p.edu.model.Firms.Firm;
//import pl.lodz.p.edu.model.Tickets.Ticket;
//import pl.lodz.p.edu.model.Trains.Train;
//import pl.lodz.p.edu.model.Users.User;
//import pl.lodz.p.edu.userInterface.ShowAllUserTicketUseCase;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//public class ConcreteTicketService implements ShowAllUserTicketUseCase {
//
//    List<Ticket> tickets = new ArrayList<>();
//
//    ConcreteTicketService() {
//        Ticket ticket1 = new Ticket();
//        User user1 = new User();
//        Train train1 = new Train();
//        Firm firm = new Firm();
//
//        ticket1.setTicketId(UUID.randomUUID());
//
//        firm.setFirmId(UUID.randomUUID());
//        firm.setName("TestFirm");
//
//        train1.setFirm(firm);
//        train1.setName("TestTrain");
//        train1.setSeats(10);
//        train1.setTrainId(UUID.randomUUID());
//        train1.setTicketID(ticket1.getTicketId());
//
//        user1.setUserId(UUID.fromString("7d6cf9b9-c06b-4944-9e55-52e87148080e"));
//        user1.setIsActive(true);
//        user1.setType("Client");
//        user1.setPassword("testPassword123");
//        user1.setName("testName");
//        user1.setEmail("testEmail@wp.pl");
//
//        ticket1.setUser(user1);
//        ticket1.setTrain(train1);
//        ticket1.setStartingLocalDate(LocalDate.now());
//        ticket1.setEndingLocalDate(LocalDate.now().plusDays(1));
//
//        tickets.add(ticket1);
//
//    }
//    @Override
//    public List<Ticket> getTickets(User u) {
//        List<Ticket> userTickets = new ArrayList<>();
//        for (Ticket ticket:
//             tickets) {
//            if (ticket.getUser().getUserId().equals(u.getUserId())) {
//                userTickets.add(ticket);
//            }
//        }
//        return userTickets;
//    }
//}
