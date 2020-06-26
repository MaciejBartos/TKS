package pl.lodz.p.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.model.FirmsGUI.FirmGUI;
import pl.lodz.p.edu.model.TicketsGUI.TicketGUI;
import pl.lodz.p.edu.model.TrainsGUI.ExpressTrainGUI;
import pl.lodz.p.edu.model.TrainsGUI.TrainTypeGUI;
import pl.lodz.p.edu.model.TrainsGUI.PassengerTrainGUI;
import pl.lodz.p.edu.model.TrainsGUI.TrainGUI;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestMapping("/trains")
@Controller
public class TrainController {



    private final RestTemplate restTemplate;
    private final String restURL = "http://localhost:8445/api/v1/trains/";
    private final String restURLFirm = "http://localhost:8445/api/v1/firms/";
    private final String restURLTicket = "http://localhost:8445/api/v1/tickets/";

    @Autowired
    public TrainController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private List<FirmGUI> firmsGet(){
        return restTemplate.exchange(restURLFirm, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<FirmGUI>>() {
                }).getBody();
    }

    private TicketGUI ticketGet(UUID id){
        return restTemplate.getForEntity(restURLTicket + id.toString(), TicketGUI.class)
                .getBody();
    }

    private List<TrainGUI> getTrains() {
        List<TrainGUI> trainsToReturn = new LinkedList<>();
        List<ExpressTrainGUI> trains = restTemplate.exchange(restURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ExpressTrainGUI>>() {
                }).getBody();
        for (ExpressTrainGUI train : trains
        ) {
            if (train.getCarriage() == 0) {
                PassengerTrainGUI passengerTrain = new PassengerTrainGUI(train.getName(), train.getSeats(), train.getFirm());
                passengerTrain.setTrainId(train.getTrainId());
                trainsToReturn.add(passengerTrain);
            } else {
                trainsToReturn.add(train);
            }
        }
        return trainsToReturn;
    }

    private List<TrainGUI> sort(String text) {
        return Objects.requireNonNull(restTemplate.exchange(restURL + "sort/" + text,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrainGUI>>() {
                }).getBody());
    }

    private void postPassengerTrain(TrainGUI train) {
        restTemplate.postForObject(restURL + "passenger", train, PassengerTrainGUI.class);
    }

    private void postExpressTrain(TrainGUI train) {
        restTemplate.postForObject(restURL + "express", train, ExpressTrainGUI.class);
    }

    private void putExpressTrain(TrainGUI train) {
        restTemplate.put(restURL + "express/" + train.getTrainId().toString(), train, ExpressTrainGUI.class);
    }

    private void putPassengerTrain(TrainGUI train) {
        restTemplate.put(restURL + "passenger/" + train.getTrainId().toString(), train, PassengerTrainGUI.class);
    }

    private void deleteTrain(UUID id) {
        restTemplate.delete(restURL + id.toString());
    }

    private TrainGUI getTrain(UUID id) {
        System.out.println(id);
        ExpressTrainGUI train = restTemplate.getForObject(restURL + id.toString(), ExpressTrainGUI.class);
        if (train.getCarriage() == 0) {
            return restTemplate.getForObject(restURL + id.toString(), PassengerTrainGUI.class);
        }
        return train;
    }

    @GetMapping
    public String s(Model model) {
        model.addAttribute("trains", getTrains());
        model.addAttribute("text", new TrainTypeGUI(""));
        return "Train/index";
    }

    @GetMapping("/add/express")
    public String addSiteE(Model model) {
        model.addAttribute("train", new ExpressTrainGUI());
        model.addAttribute("trainType", new TrainTypeGUI("express"));
        model.addAttribute("firms", firmsGet());

        return "Train/create";
    }

    @GetMapping("/add/passenger")
    public String addSiteP(Model model) {
        model.addAttribute("train", new PassengerTrainGUI());
        model.addAttribute("trainType", new TrainTypeGUI("passenger"));
        model.addAttribute("firms", firmsGet());

        return "Train/create";
    }

    @PostMapping("/add/express")
    public String addTrain(@Valid @ModelAttribute("train") ExpressTrainGUI train, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("firms", firmsGet());
            model.addAttribute("trainType", new TrainTypeGUI("express"));
            return "Train/create";
        }
        postExpressTrain(train);
        return "redirect:/trains";
    }

    @PostMapping("/add/passenger")
    public String addTrain(@Valid @ModelAttribute("train") PassengerTrainGUI train, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("firms", firmsGet());
            model.addAttribute("trainType", new TrainTypeGUI("passenger"));
            return "Train/create";
        }
        postPassengerTrain(train);
        return "redirect:/trains";
    }

    @GetMapping("/edit/{id}")
    public String editSite(@PathVariable UUID id, Model model) {
        TrainGUI t = getTrain(id);
        if (t instanceof ExpressTrainGUI) {
            model.addAttribute("trainType", new TrainTypeGUI("express"));
        } else if (t instanceof PassengerTrainGUI) {
            model.addAttribute("trainType", new TrainTypeGUI("passenger"));
        }
        model.addAttribute("train", t);
        model.addAttribute("firms", firmsGet());
        return "Train/edit";
    }

    @PostMapping("/edit/{id}/express")
    public String editExpress(@PathVariable UUID id, @Valid @ModelAttribute("train") ExpressTrainGUI train, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            train.setTrainId(id);
            model.addAttribute("train", train);
            model.addAttribute("trainType", new TrainTypeGUI("express"));
            model.addAttribute("firms", firmsGet());
            return "Train/edit";
        }
        train.setTrainId(id);
        putExpressTrain(train);
        return "redirect:/trains";
    }

    @PostMapping("/edit/{id}/passenger")
    public String editPassenger(@PathVariable UUID id, @Valid @ModelAttribute("train") PassengerTrainGUI train, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            train.setTrainId(id);
            model.addAttribute("train", train);
            model.addAttribute("trainType", new TrainTypeGUI("passenger"));
            model.addAttribute("firms", firmsGet());
            return "Train/edit";
        }
        train.setTrainId(id);
        putPassengerTrain(train);
        return "redirect:/trains";
    }

    @GetMapping("/train/{id}")
    public String info(@PathVariable UUID id, Model model) {
        TrainGUI train = getTrain(id);
        model.addAttribute("train", train);
        if (train.getTicketID() != null) {
            model.addAttribute("ticket", ticketGet(train.getTicketID()));
        } else {
            model.addAttribute("ticket", null);
        }
        return "Train/info";
    }

    @GetMapping("/delete/{id}")
    public String delTrain(@PathVariable UUID id) {
        deleteTrain(id);
        return "redirect:/trains";
    }

    @GetMapping("sort")
    public String sort(@ModelAttribute("text") TrainTypeGUI text, Model model) {
        if (text.getType() == "") {
            model.addAttribute("trains", getTrains());
        } else {
            model.addAttribute("trains", sort(text.getType()));
        }
        model.addAttribute("text", text);
        return "Train/index";
    }
}
