package pl.lodz.p.edu.soap.services;

import pl.lodz.p.edu.soap.method.Registration;
import pl.lodz.p.edu.soap.method.Response;
import pl.lodz.p.edu.soap.model.TicketDTO;
import pl.lodz.p.edu.soap.model.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.UUID;

@WebService
public interface IUserSoapService {

    @WebMethod
    Response registerAccount(Registration user);

    @WebMethod
    List<UserDTO> getAllAccounts();

    @WebMethod
    UserDTO getAccount(String id);

    @WebMethod
    Response updateUser(UserDTO user);

//    @WebMethod
//    List<TicketDTO> getUserTickets(String id);

    @WebMethod
    void changeAccountState(String id);

}
