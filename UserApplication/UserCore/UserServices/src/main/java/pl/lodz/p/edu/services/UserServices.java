package pl.lodz.p.edu.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Users.Admin;
import pl.lodz.p.edu.model.Users.Client;
import pl.lodz.p.edu.model.Users.ResourcesManager;
import pl.lodz.p.edu.model.Users.User;
import pl.lodz.p.edu.userInterface.IUserService;
import pl.lodz.p.edu.userInterface.ShowAllItemsUseCase;
import pl.lodz.p.edu.userInterface.ShowUserByNameUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServices implements IUserService, ShowUserByNameUseCase, ShowAllItemsUseCase<User> {

    // private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final IAddItem<User> userAdd;
    private final IDeleteItem<User> userDelete;
    private final IUpdateItem<User> userUp;
    private final IGetAllItems<User> usersGet;
    private final IGetItem<User> userGet;
    private final ISortItems<User> userSort;
    private final ILoadUserByEmail userGetByEmail;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserServices(IAddItem<User> userAdd, IDeleteItem<User> userDelete, IUpdateItem<User> userUp, IGetAllItems<User> usersGet, IGetItem<User> userGet, ISortItems<User> userSort, ILoadUserByEmail userGetByEmail, RabbitTemplate rabbitTemplate) {
        this.userAdd = userAdd;
        this.userDelete = userDelete;
        this.userUp = userUp;
        this.usersGet = usersGet;
        this.userGet = userGet;
        this.userSort = userSort;
        this.userGetByEmail = userGetByEmail;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "createUserUserService")
    public void add(User u) {
        try {
            switch (u.getType()) {
                case "Admin":
                    userAdd.add(new Admin(u.getName(), u.getUserId(), u.getIsActive(), u.getEmail(), u.getPassword()));
                    break;
                case "ResourcesManager":
                    userAdd.add(new ResourcesManager(u.getName(), u.getUserId(), u.getIsActive(), u.getEmail(), u.getPassword()));
                    break;
                case "Client":
                    userAdd.add(new Client(u.getName(), u.getUserId(), u.getIsActive(), u.getEmail(), u.getPassword()));
                    break;
            }
        } catch (Exception e) {
            rabbitTemplate.convertAndSend("deleteUserRentService", "", u);
        }
    }

    public List<User> getAll() {
        return usersGet.getAll();
    }

    public User get(UUID id) {
        Optional<User> u = userGet.getById(id);
        if (u.isPresent()) {
            return u.get();
        } else {
            return new User();
        }
    }

    public User get(String email) {
        Optional<User> u = userGetByEmail.getByEmail(email);
        if (u.isPresent()) {
            return u.get();
        } else {
            return new User();
        }
    }

    @RabbitListener(queues = "changeStateUserService")
    public void changeState(UUID id) {
        Optional<User> u = userGet.getById(id);
        if (u.isPresent()) {
            u.get().changeState();
            try {
                userUp.update(u.get());
            } catch (Exception e) {
                rabbitTemplate.convertAndSend("changeStateRentService", "", u.get().getUserId());
            }
        }
    }

    @RabbitListener(queues = "editUserUserService")
    public void update(User user) {
        try {
            userUp.update(user);
        } catch (Exception e) {
            User userFromDB = userGet.getById(user.getUserId()).get();
            rabbitTemplate.convertAndSend("editUserRentService", "", userFromDB);
        }
    }

    public List<User> sort(String text) {
        return userSort.sort(text);
    }

    public List<User> getActiveUsers() {
        List<User> users = new ArrayList<>();
        for (User user : usersGet.getAll()
        ) {
            if (user.getIsActive()) {
                users.add(user);
            }
        }
        return users;
    }

    @Override
    @RabbitListener(queues = "deleteUserUserService")
    public void deleteUser(User item) {
        try {
            userDelete.delete(item);
        } catch (Exception e) {
            rabbitTemplate.convertAndSend("createUserRentService", "", item);
        }
    }
}
