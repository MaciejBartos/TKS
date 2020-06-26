//package pl.lodz.p.edu.soap.services;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.event.annotation.AfterTestMethod;
//import org.springframework.test.context.junit4.SpringRunner;
//import pl.lodz.p.edu.model.Users.User;
//import pl.lodz.p.edu.soap.converter.ToDomainModel;
//import pl.lodz.p.edu.soap.method.Registration;
//import pl.lodz.p.edu.soap.method.Response;
//import pl.lodz.p.edu.soap.model.TicketDTO;
//import pl.lodz.p.edu.soap.model.UserDTO;
//
//
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//
//class UserSoapServiceTest {
//
//    private final UserSoapService userSoapService;
//
//    public UserSoapServiceTest() {
//        userSoapService = new UserSoapService(new ConcreteUserService());
//    }
//
//
//    @Test
//    void registerAccount() {
//
//        Registration registration = new Registration();
//        registration.setPassword("testPassword123");
//        registration.setName("Testname");
//        registration.setEmail("testEmail@wp.pl");
//
//        Response response = userSoapService.registerAccount(registration);
//        List<UserDTO> users = userSoapService.getAllAccounts();
//
//        assertEquals(200, response.getResponseNumber());
//        assertEquals(2, users.size());
//
//    }
//
//    @Test
//    void getAllAccounts() {
//        List<UserDTO> users = userSoapService.getAllAccounts();
//
//        assertEquals(1, users.size());
//        assertEquals( "Usertestname", users.get(0).getName());
//    }
//
//    @Test
//    void getAccount() {
//
//        UserDTO user = userSoapService.getAccount("7d6cf9b9-c06b-4944-9e55-52e87148080e");
//
//        assertEquals("7d6cf9b9-c06b-4944-9e55-52e87148080e", user.getUserId());
//    }
//
//    @Test
//    void updateUser() {
//        UserDTO user = userSoapService.getAccount("7d6cf9b9-c06b-4944-9e55-52e87148080e");
//        user.setName("secondTestName");
//        userSoapService.updateUser(user);
//
//        UserDTO user2 = userSoapService.getAccount("7d6cf9b9-c06b-4944-9e55-52e87148080e");
//
//        assertEquals("secondTestName", user2.getName());
//    }
//
////    @Test
////    void getUserTickets() {
////        List<TicketDTO> tickets = userSoapService.getUserTickets("7d6cf9b9-c06b-4944-9e55-52e87148080e");
////
////        assertEquals(1, tickets.size());
////    }
//
//    @Test
//    void changeAccountState() {
//        assertTrue(userSoapService.getAccount("7d6cf9b9-c06b-4944-9e55-52e87148080e").isActive());
//
//        userSoapService.changeAccountState("7d6cf9b9-c06b-4944-9e55-52e87148080e");
//
//        assertFalse(userSoapService.getAccount("7d6cf9b9-c06b-4944-9e55-52e87148080e").isActive());
//    }
//
//}