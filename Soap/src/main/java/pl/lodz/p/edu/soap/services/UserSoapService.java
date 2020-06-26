//package pl.lodz.p.edu.soap.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//import pl.lodz.p.edu.model.Users.User;
//import pl.lodz.p.edu.soap.converter.FromDomainModel;
//import pl.lodz.p.edu.soap.converter.ToDomainModel;
//import pl.lodz.p.edu.soap.method.Registration;
//import pl.lodz.p.edu.soap.method.Response;
//import pl.lodz.p.edu.soap.model.TicketDTO;
//import pl.lodz.p.edu.soap.model.UserDTO;
//import pl.lodz.p.edu.userInterface.*;
//
//
//import javax.annotation.PostConstruct;
//import javax.jws.WebMethod;
//import javax.jws.WebService;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@WebService(endpointInterface = "pl.lodz.p.edu.soap.services.IUserSoapService")
//@Component
//public class UserSoapService implements IUserSoapService {
//
//    private IUserService userService;
//    //private ShowAllUserTicketUseCase ticketService;
//
//    public UserSoapService() {
//    }
//
//    public UserSoapService(IUserService userService) {
//        this.userService = userService;
//    }
//
//    @Autowired
//    public void setUserService(IUserService userService) {
//        this.userService = userService;
//    }
//
////    @Autowired
////    public void setTicketService(ShowAllUserTicketUseCase ticketService) {
////        this.ticketService = ticketService;
////    }
//
//    @PostConstruct
//    public void init() {
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//    }
//
//
//    @WebMethod
//    @Override
//    public Response registerAccount(Registration registration) {
//        UserDTO user = new UserDTO();
//        user.setEmail(registration.getEmail());
//        user.setActive(true);
//        user.setName(registration.getName());
//        user.setPassword(registration.getPassword());
//        user.setType("Client");
//        user.setUserId(UUID.randomUUID().toString());
//
//        Response response = new Response();
//
//        try {
//            userService.add(ToDomainModel.fromUserDTOtoUser(user));
//            response.setResponseNumber(200);
//            response.setResponseMessage("Account created!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.setResponseNumber(500);
//            response.setResponseMessage("Error occurred on creating account!");
//        }
//        return response;
//    }
//
//    @WebMethod
//    @Override
//    public List<UserDTO> getAllAccounts() {
//        List<User> users = userService.getAll();
//        List<UserDTO> usersDTO = new ArrayList<>();
//
//        for (User user :
//                users) {
//            usersDTO.add(FromDomainModel.fromUserToUserDTO(user));
//        }
//
//        return usersDTO;
//    }
//
//    @WebMethod
//    @Override
//    public UserDTO getAccount(String id) {
//        return FromDomainModel.fromUserToUserDTO(userService.get(UUID.fromString(id)));
//    }
//
//    @WebMethod
//    @Override
//    public Response updateUser(UserDTO user) {
//        Response response = new Response();
//
//        try {
//            userService.update(ToDomainModel.fromUserDTOtoUser(user));
//            response.setResponseMessage("ok");
//            response.setResponseNumber(200);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.setResponseNumber(500);
//            response.setResponseMessage("Error occurred on updating account!");
//        }
//
//        return response;
//
//    }
//
////    @WebMethod
////    @Override
////    public List<TicketDTO> getUserTickets(String id) {
////        List<Ticket> tickets = ticketService.getTickets(userService.get(UUID.fromString(id)));
////        List<TicketDTO> ticketDTOs = new ArrayList<>();
////
////        for (Ticket ticket :
////                tickets) {
////            ticketDTOs.add(FromDomainModel.fromTicketToTicketDTO(ticket));
////        }
////
////        return ticketDTOs;
////    }
//
//    @WebMethod
//    @Override
//    public void changeAccountState(String id) {
//        userService.changeState(UUID.fromString(id));
//    }
//
//
//}
