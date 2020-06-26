package pl.lodz.p.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.model.TicketsGUI.TicketGUI;
import pl.lodz.p.edu.model.TrainsGUI.TrainGUI;
import pl.lodz.p.edu.model.TrainsGUI.TrainTypeGUI;
import pl.lodz.p.edu.model.UsersGUI.UserGUI;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/tickets")
@Controller
public class TicketController {

    //private final ShowAllItemsUseCase<Ticket> ticketsGet;
    //private final SaveItemUseCase<Ticket> ticketAdd;
    //private final SortTicketsByTrainUseCase trainSort;
   // private final RemoveTicketUseCase ticketDel;
   // private final SortTicketsByUserClientUseCase userSort;
   // private final ShowUserClientByNameUseCase userByEmail;
   // private final ShowAllActiveUsersClientUseCase activeUsers;
   // private final ShowNoAllocatedTrainsUseCase noAllocatedTrains;
  //  private final FinishAllocationUseCase allocatedTicket;
    //private final ShowAllUserClientTicketUseCase ticketsUser;
   // private final ShowItemUseCase<Train> trainGet;
   // private final ShowItemUseCase<UserGUI> userGet;
    private final RestTemplate restTemplate;
    private String restURL= "http://localhost:8445/api/v1/tickets/";
    private String restURLUser= "http://localhost:8445/api/v1/userClients/";
    private String restURLTrain= "http://localhost:8445/api/v1/trains/";

    private List<TicketGUI> ticketsUser(UserGUI user){
        return restTemplate.exchange(restURL + "userTickets/" + user.getUserId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TicketGUI>>() {
                }).getBody();
    }

    private void allocatedTicket(UUID id){
        restTemplate.getForObject(restURL + "finish/" + id.toString(), String.class);
    }

    private List<TrainGUI> noAllocatedTrains(){
        return restTemplate.exchange(restURLTrain + "notAllocated",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrainGUI>>() {
                }).getBody();
    }

    private List<UserGUI> activeUsers(){
        return restTemplate.exchange(restURLUser + "activeUsers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserGUI>>() {
                }).getBody();
    }

    private UserGUI userByEmail(String emaiil){
        return restTemplate.getForObject(restURLUser + "email/" + emaiil, UserGUI.class);
    }

    private List<TicketGUI> userSort(String text){
        return restTemplate.exchange(restURL + "sortUser/" + text,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TicketGUI>>() {
                }).getBody();
    }

    private List<TicketGUI> ticketsGet() {
        List<TicketGUI> trainsToReturn = new LinkedList<>();
        return  restTemplate.exchange(restURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TicketGUI>>() {
                }).getBody();
    }

    private List<TicketGUI> trainSort(String text) {
        return Objects.requireNonNull(restTemplate.exchange(restURL + "sortTrain/" + text,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TicketGUI>>() {
                }).getBody());
    }

    private void ticketAdd(TicketGUI ticket) {
        restTemplate.postForObject(restURL + "passenger", ticket, TicketGUI.class);
    }

    private void ticketDel(UUID id) {
        restTemplate.delete(restURL + id.toString());
    }

    private UserGUI userGet(UUID id) {
        return restTemplate.getForObject(restURLUser + id.toString(), UserGUI.class);
    }

    private TrainGUI trainGet(UUID id) {
        return restTemplate.getForObject(restURLTrain + id.toString(), TrainGUI.class);
    }

    @Autowired
    public TicketController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("tickets", ticketsGet());
        model.addAttribute("textUser", new TrainTypeGUI(""));
        model.addAttribute("textTrain", new TrainTypeGUI(""));
        return "Ticket/index";

    }

    @GetMapping("/add")
    public String addSite(Model model, Authentication authentication) {

        if (authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
            List<UserGUI> users = new ArrayList<>();
            users.add(userByEmail(authentication.getName()));
            model.addAttribute("users", users);
        } else {
            model.addAttribute("users", activeUsers());
        }
        model.addAttribute("trains", noAllocatedTrains());
        model.addAttribute("ticket", new TicketGUI());
        model.addAttribute("user", new UserGUI());
        model.addAttribute("train", new TrainGUI());
        return "Ticket/create";

    }

    @PostMapping("/add")
    public String addTicket(Authentication authentication, @Valid @ModelAttribute("ticket") TicketGUI ticket, BindingResult bindingResult, @Valid @ModelAttribute("train") TrainGUI train, BindingResult bindingResult1, @Valid @ModelAttribute("user") UserGUI user, BindingResult bindingResult2, Model model) {
        if (bindingResult.hasErrors()) {

            if (authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
                List<UserGUI> users = new ArrayList<>();
                users.add(userByEmail(authentication.getName()));
                model.addAttribute("users", users);
            } else {
                model.addAttribute("users", activeUsers());
            }
            model.addAttribute("trains", noAllocatedTrains());
            model.addAttribute("ticket", new TicketGUI());
            model.addAttribute("user", new UserGUI());
            model.addAttribute("train", new TrainGUI());
            return "Ticket/create";
        }
        ticket.setTrain(trainGet(train.getTrainId()));
        ticket.setUser(userGet(user.getUserId()));
        try {
            ticketAdd(ticket);
        } catch (Exception e) {
            model.addAttribute("exception", e);
            if (authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
                List<UserGUI> users = new ArrayList<>();
                users.add(userByEmail(authentication.getName()));
                model.addAttribute("users", users);
            } else {
                model.addAttribute("users", activeUsers());
            }
            model.addAttribute("trains", noAllocatedTrains());
            model.addAttribute("ticket", new TicketGUI());
            model.addAttribute("user", new UserGUI());
            model.addAttribute("train", new TrainGUI());
            return "Ticket/create";
        }
        if (authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
            return "redirect:/home";
        }
        return "redirect:/tickets";

    }

    @GetMapping("/end/{id}")
    public String end(@PathVariable UUID id) {
        allocatedTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable UUID id) {
        ticketDel(id);
        return "redirect:/tickets";
    }

    @GetMapping("sortUser")
    public String sortUser(@ModelAttribute("textUser") TrainTypeGUI text, Model model) {
        if (text.getType() == "") {
            model.addAttribute("tickets", ticketsGet());
        } else {
            model.addAttribute("tickets", userSort(text.getType()));
        }
        model.addAttribute("textUser", text);
        model.addAttribute("textTrain", new TrainTypeGUI(""));
        return "Ticket/index";
    }

    @GetMapping("sortTrain")
    public String sortTrain(@ModelAttribute("textTrain") TrainTypeGUI text, Model model) {
        if (text.getType() == "") {
            model.addAttribute("tickets", ticketsGet());
        } else {
            model.addAttribute("tickets", trainSort(text.getType()));
        }
        model.addAttribute("textTrain", text);
        model.addAttribute("textUser", new TrainTypeGUI(""));
        return "Ticket/index";
    }

    @GetMapping("show")
    public String show(Model model, Authentication authentication) {
        UserGUI user = userByEmail(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("tickets", ticketsUser(user));
        return "Base/clientTickets";
    }

    @GetMapping("/user/{id}")
    public String info(@PathVariable UUID id, Model model) {
        UserGUI user = userGet(id);
        model.addAttribute("user", user);
        model.addAttribute("tickets", ticketsUser(user));
        return "User/info";
    }
}
