package pl.lodz.p.edu.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Clients.UserClient;
import pl.lodz.p.edu.userInterface.IUserClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserClientService implements IUserClientService {

    private final IAddItem<UserClient> userAdd;
    private final IDeleteItem<UserClient> userDelete;
    private final IUpdateItem<UserClient> userUp;
    private final IGetAllItems<UserClient> usersGet;
    private final IGetItem<UserClient> userGet;
    private final ISortItems<UserClient> userSort;
    private final ILoadUserClientByEmail userGetByEmail;
    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public UserClientService(IAddItem<UserClient> userAdd, IDeleteItem<UserClient> userDelete, IUpdateItem<UserClient> userUp, IGetAllItems<UserClient> usersGet, IGetItem<UserClient> userGet, ISortItems<UserClient> userSort, ILoadUserClientByEmail userGetByEmail, RabbitTemplate rabbitTemplate) {
        this.userAdd = userAdd;
        this.userDelete = userDelete;
        this.userUp = userUp;
        this.usersGet = usersGet;
        this.userGet = userGet;
        this.userSort = userSort;
        this.userGetByEmail = userGetByEmail;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "createUserRentService")
    public void add(UserClient u) {
        try {
            userAdd.add(u);
        } catch (Exception e) {
            rabbitTemplate.convertAndSend("deleteUserUserService", "", u);
        }
    }

    public List<UserClient> getAll() {
        return usersGet.getAll();
    }

    public UserClient get(UUID id) {
        Optional<UserClient> u = userGet.getById(id);
        if (u.isPresent()) {
            return u.get();
        } else {
            return new UserClient();
        }

    }

    public UserClient get(String email) {
        Optional<UserClient> u = userGetByEmail.getByEmail(email);
        if (u.isPresent()) {
            return u.get();
        } else {
            return new UserClient();
        }
    }

    @RabbitListener(queues = "changeStateRentService")
    public void changeState(UUID id) {
        Optional<UserClient> u = userGet.getById(id);
        if (u.isPresent()) {
            u.get().changeState();
            try {
                userUp.update(u.get());
            } catch (Exception e) {
                rabbitTemplate.convertAndSend("changeStateUserService", "", id);
            }
        }
    }

    @RabbitListener(queues = "editUserRentService")
    public void update(UserClient user) {
        try {
            userUp.update(user);
        } catch (Exception e) {
            UserClient userFromDB = userGet.getById(user.getUserId()).get();
            rabbitTemplate.convertAndSend("editUserUserService", "", userFromDB);
        }
    }

    public List<UserClient> sort(String text) {
        return userSort.sort(text);
    }

    public List<UserClient> getActiveUsers() {
        List<UserClient> users = new ArrayList<>();
        for (UserClient user : usersGet.getAll()
        ) {
            if (user.getIsActive()) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    @RabbitListener(queues = "deleteUserRentService")
    public void delete(UserClient user) {
        try {
            userDelete.delete(user);
        } catch (Exception e) {
            rabbitTemplate.convertAndSend("createUserUserService", "", user);
        }
    }
}
