package pl.lodz.p.edu.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.edu.model.UsersGUI.AdminGUI;
import pl.lodz.p.edu.model.UsersGUI.ClientGUI;
import pl.lodz.p.edu.model.UsersGUI.ResourcesManagerGUI;
import pl.lodz.p.edu.model.UsersGUI.UserGUI;

import java.util.List;
import java.util.UUID;

@Service
public class DBInit implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public DBInit(RestTemplate restTemplate, RabbitTemplate rabbitTemplate, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        rabbitTemplate.convertAndSend("createUserExchange","",new AdminGUI("Franek", UUID.randomUUID(), true,"admin", passwordEncoder.encode("admin")));
        rabbitTemplate.convertAndSend("createUserExchange","",new ClientGUI("Kamil", UUID.randomUUID(), true,"client", passwordEncoder.encode("client")));
        rabbitTemplate.convertAndSend("createUserExchange","",new ClientGUI("Kamil", UUID.randomUUID(), true,"client1", passwordEncoder.encode("client")));
        rabbitTemplate.convertAndSend("createUserExchange","",new ResourcesManagerGUI("Maciek", UUID.randomUUID(), true,"manager",passwordEncoder.encode("manager")));
    }
}
