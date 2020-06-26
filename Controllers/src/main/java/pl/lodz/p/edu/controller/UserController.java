package pl.lodz.p.edu.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.model.TrainsGUI.TrainTypeGUI;
import pl.lodz.p.edu.model.UsersGUI.UserGUI;
import pl.lodz.p.edu.security.ReCaptchaResponse;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RequestMapping("/users")
@Controller
public class UserController {

    private final RestTemplate restTemplate;
//    private final ShowAllItemsUseCase<User> usersGet;
//    private final ShowItemUseCase<User> userGet;
//    private final SaveItemUseCase<User> userAdd;
//    private final UpgradeItemUseCase<User> userUp;
//    private final SortItemsUseCase<User> userSort;
//    private final ChangeStateClientUseCase userState;
    private final RabbitTemplate rabbitTemplate;
    private final PasswordEncoder passwordEncoder;

    private final String restURLUserClients = "https://localhost:8443/api/v1/userClients/";
    private final String restURLUsers = "http://localhost:8444/api/v1/users/";

//    boolean postConstruct = true;
    @Autowired
    public UserController(RestTemplate restTemplate, RabbitTemplate rabbitTemplate, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showAll(Model model) {
        //model.addAttribute("users", FromUserDomainConverterGUI.convertUsersList(usersGet.getAll()));
//       if(postConstruct){
//           for(UserGUI user: restTemplate.exchange(restURLUsers, HttpMethod.GET, null,
//                   new ParameterizedTypeReference<List<UserGUI>>() {
//                   }).getBody()
//           ){
//               rabbitTemplate.convertAndSend("createUserRentService",user);
//
//           }
//           postConstruct = false;
//       }
        model.addAttribute("users",restTemplate.exchange(restURLUsers, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserGUI>>() {
                }).getBody());
        model.addAttribute("text", new TrainTypeGUI(""));
        return "User/index";
    }

    @GetMapping("/state/{id}")
    public String changeState(@PathVariable UUID id, Model model) {
        //userState.changeState(id);
        rabbitTemplate.convertAndSend("changeStateExchange","", id);
        return "redirect:/users";
    }

    @GetMapping("/add")
    public String addSite(Model model) {
        model.addAttribute("user", new UserGUI());
        return "User/create";
    }

    @GetMapping("/registration")
    public String regiser(Model model){
        model.addAttribute("user", new UserGUI());
        return "Base/registration";
    }

    @PostMapping("registration")
    public String registration(@Valid @ModelAttribute("user") UserGUI user, BindingResult bindingResult, Model model, @RequestParam(name = "g-recaptcha-response") String recaptcha) throws Exception {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LdXkcsUAAAAANpKqy-xdtpBjiWbj5IWYo4J_74o&response=" + recaptcha;
        ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url + params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();

        if (reCaptchaResponse.isSuccess()) {
            if (bindingResult.hasErrors()) {
                return "Base/registration";
            }
            user.setType("Client");
            user.setIsActive(false);
            rabbitTemplate.convertAndSend("createUserExchange","",user);
//            userAdd.add(ToUserDomainConverterGUI.convertUser(user));
            return "redirect:/home";
        }
        return "Base/registration";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("user") UserGUI user, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new UserGUI());
            return "User/create";
        }

        //userAdd.add(ToUserDomainConverterGUI.convertUser(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        rabbitTemplate.convertAndSend("createUserExchange","",user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editSite(@PathVariable UUID id, Model model) {
//        model.addAttribute("user", FromUserDomainConverterGUI.convertUser(userGet.get(id)));
        model.addAttribute("user", restTemplate.getForObject(restURLUsers + id.toString(), UserGUI.class));
        return "User/edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable UUID id, UserGUI user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            user.setUserId(id);
            model.addAttribute("user", user);
            return "User/edit";
        }
        user.setUserId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        rabbitTemplate.convertAndSend("editUserExchange","",user);
        //userUp.update(ToUserDomainConverterGUI.convertUser(user));
        return "redirect:/users";
    }

    @GetMapping("sort")
    public String sort(@ModelAttribute("text") TrainTypeGUI text, Model model) {
        if (text.getType().equals("")) {
//            model.addAttribute("users", FromUserDomainConverterGUI.convertUsersList(usersGet.getAll()));
            model.addAttribute("users", restTemplate.exchange(restURLUsers, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<UserGUI>>() {
                    }).getBody());
        } else {
           // model.addAttribute("users", FromUserDomainConverterGUI.convertUsersList(userSort.sort(text.getType())));
            model.addAttribute("users", restTemplate.exchange(restURLUsers + "sort/" + text, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<UserGUI>>() {
                    }).getBody());
        }
        model.addAttribute("text", text);
        return "User/index";
    }

    @GetMapping("fromRent")
    public String getUserClients(Model model){
        model.addAttribute("users", restTemplate.exchange(restURLUserClients, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserGUI>>() {
                }).getBody());
        model.addAttribute("text", new TrainTypeGUI(""));
        return "User/index";
    }
}
