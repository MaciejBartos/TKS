package pl.lodz.p.edu.soap.services;

import pl.lodz.p.edu.model.Users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import pl.lodz.p.edu.userInterface.IUserService;


public class ConcreteUserService implements IUserService {

    private List<User> users = new ArrayList<>();

    ConcreteUserService() {
        User user1 = new User();

        user1.setUserId(UUID.fromString("7d6cf9b9-c06b-4944-9e55-52e87148080e"));
        user1.setEmail("user1TestEmail@wp.pl");
        user1.setName("Usertestname");
        user1.setPassword("testPasswordUser1");
        user1.setType("Client");
        user1.setIsActive(true);

        users.add(user1);
    }


    @Override
    public void changeState(UUID id) {
        for (User user :
                users) {
            if (user.getUserId().equals(id)) {
                user.changeState();
            }
        }
    }

    @Override
    public void add(User item) throws Exception {
        users.add(item);
    }

    @Override
    public List<User> getActiveUsers() {
        return null;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }

    @Override
    public User get(UUID id) {
        for (User user :
                users) {
            if (user.getUserId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User get(String name) {
        for (User user :
                users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> sort(String text) {
        return null;
    }

    @Override
    public void update(User item) {
        for (User user :
                users) {
            if (item.getUserId().equals(user.getUserId())) {
                users.remove(user);
                users.add(item);
            }
        }
    }
}
