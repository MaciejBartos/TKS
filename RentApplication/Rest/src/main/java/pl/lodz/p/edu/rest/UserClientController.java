package pl.lodz.p.edu.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.model.Clients.UserClient;
import pl.lodz.p.edu.userInterface.ShowAllActiveUsersClientUseCase;
import pl.lodz.p.edu.userInterface.ShowAllItemsUseCase;
import pl.lodz.p.edu.userInterface.ShowItemUseCase;
import pl.lodz.p.edu.userInterface.ShowUserClientByNameUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/userClients")
public class UserClientController {

    private final ShowAllItemsUseCase<UserClient> usersGet;
    private final ShowItemUseCase<UserClient> userGet;
    private final ShowAllActiveUsersClientUseCase activeUsers;
    private final ShowUserClientByNameUseCase userByEmail;

    @Autowired
    public UserClientController(ShowAllItemsUseCase<UserClient> usersGet, ShowItemUseCase<UserClient> userGet, ShowAllActiveUsersClientUseCase activeUsers, ShowUserClientByNameUseCase userByEmail) {
        this.usersGet = usersGet;
        this.userGet = userGet;
        this.activeUsers = activeUsers;
        this.userByEmail = userByEmail;
    }

    @GetMapping
    public List<UserClient> getAll() {
        return usersGet.getAll();
    }

    @GetMapping(path = "{id}")
    public UserClient get(@PathVariable UUID id){
        return userGet.get(id);
    }

    @GetMapping("activeUsers")
    public List<UserClient> activeUs(){
        return activeUsers.getActiveUsers();
    }

    @GetMapping("email/{email}")
    public UserClient usByEmail(@PathVariable String email){
        return userByEmail.get(email);
    }
}
