package pl.lodz.p.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.model.Tickets.Ticket;
import pl.lodz.p.edu.model.Trains.Train;
import pl.lodz.p.edu.model.Trains.TrainType;
import pl.lodz.p.edu.model.Users.User;
import pl.lodz.p.edu.service.TicketService;
import pl.lodz.p.edu.service.TrainService;
import pl.lodz.p.edu.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/tickets")
@Controller
public class TicketController {

    private TicketService ticketService;
    private TrainService trainService;
    private UserService userService;

    @Autowired
    public TicketController(TicketService ticketService, TrainService trainService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.trainService = trainService;
    }

    @GetMapping
    public String showAll(Model model){
        model.addAttribute("tickets",ticketService.getTickets());
        model.addAttribute("textUser",new TrainType(""));
        model.addAttribute("textTrain",new TrainType(""));
        return "Ticket/index";

    }

    @GetMapping("/add")
    public String addSite(Model model, Authentication authentication){

        if(authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
            List<User> users = new ArrayList<>();
            users.add(ticketService.getUserByEmail(authentication.getName()));
            model.addAttribute("users", users);
        }
        else{
            model.addAttribute("users",ticketService.getAllActiveUser());
        }
        model.addAttribute("trains",ticketService.getAllNoAllocatedTrains());
        model.addAttribute("ticket",new Ticket());
        model.addAttribute("user",new User());
        model.addAttribute("train",new Train());
        return "Ticket/create";

    }

    @PostMapping("/add")
    public String addTrain(Authentication authentication,@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, @Valid @ModelAttribute("train") Train train, BindingResult bindingResult1,@Valid @ModelAttribute("user") User user, BindingResult bindingResult2, Model model){
        if(bindingResult.hasErrors()){

            if(authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
                List<User> users = new ArrayList<>();
                users.add(ticketService.getUserByEmail(authentication.getName()));
                model.addAttribute("users", users);
            }
            else{
                model.addAttribute("users",ticketService.getAllActiveUser());
            }
            model.addAttribute("trains",ticketService.getAllNoAllocatedTrains());
            return "Ticket/create";
        }
        ticket.setTrain(train);
        ticket.setUser(user);
        try {
            ticketService.addTicket(ticket);
        }
        catch (Exception e){
            model.addAttribute("exception",e);
            if(authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
                List<User> users = new ArrayList<>();
                users.add(ticketService.getUserByEmail(authentication.getName()));
                model.addAttribute("users", users);
            }
            else{
                model.addAttribute("users",ticketService.getAllActiveUser());
            }
            model.addAttribute("trains",ticketService.getAllNoAllocatedTrains());
            return "Ticket/create";
        }
        if(authentication.getAuthorities().stream().findFirst().get().equals(new SimpleGrantedAuthority("ROLE_Client"))) {
            return "redirect:/home";
        }
        return "redirect:/tickets";

    }
    @GetMapping("/end/{id}")
    public String end(@PathVariable UUID id){
        ticketService.endTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable UUID id){
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }

    @GetMapping("sortUser")
    public String sortUser(@ModelAttribute("textUser") TrainType text, Model model){
        if(text.getType() == ""){
            model.addAttribute("tickets",ticketService.getTickets());
        }
        else{
            model.addAttribute("tickets",ticketService.sortUsers(text.getType()));
        }
        model.addAttribute("textUser",text);
        model.addAttribute("textTrain",new TrainType(""));
        return "Ticket/index";
    }

    @GetMapping("sortTrain")
    public String sortTrain(@ModelAttribute("textTrain") TrainType text, Model model){
        if(text.getType() == ""){
            model.addAttribute("tickets",ticketService.getTickets());
        }
        else{
            model.addAttribute("tickets",ticketService.sortTrains(text.getType()));
        }
        model.addAttribute("textTrain",text);
        model.addAttribute("textUser",new TrainType(""));
        return "Ticket/index";
    }

    @GetMapping("show")
    public String show(Model model,Authentication authentication){
        model.addAttribute("user",userService.getUser(authentication.getName()));
        return "Base/clientTickets";
    }

}
