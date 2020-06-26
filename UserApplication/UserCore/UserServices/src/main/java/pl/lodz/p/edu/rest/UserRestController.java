package pl.lodz.p.edu.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.edu.model.Users.User;
import pl.lodz.p.edu.userInterface.ShowAllItemsUseCase;
import pl.lodz.p.edu.userInterface.ShowItemUseCase;
import pl.lodz.p.edu.userInterface.ShowUserByNameUseCase;
import pl.lodz.p.edu.userInterface.SortItemsUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final ShowAllItemsUseCase<User> usersGet;
    private final ShowItemUseCase<User> userGet;
    private final SortItemsUseCase<User> userSort;
    private final ShowUserByNameUseCase userByName;

    @Autowired
    public UserRestController(ShowAllItemsUseCase<User> usersGet, ShowItemUseCase<User> userGet, SortItemsUseCase<User> userSort, ShowUserByNameUseCase userByName) {
        this.usersGet = usersGet;
        this.userGet = userGet;
        this.userSort = userSort;
        this.userByName = userByName;
    }

    @GetMapping
    public List<User> getAll() {
        return usersGet.getAll();
    }

    @GetMapping(path = "{id}")
    public User get(@PathVariable UUID id){
        return userGet.get(id);
    }

    @GetMapping("/sort/{text}")
    public List<User> sort(@PathVariable String text){
        return userSort.sort(text);
    }

    @GetMapping("/name/{name}")
    public User getByName(@PathVariable String name){
        return userByName.get(name);
    }
}
