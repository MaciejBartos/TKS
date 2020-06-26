package pl.lodz.p.edu.userInterface;

import pl.lodz.p.edu.model.Users.User;

import java.util.List;

public interface ShowAllActiveUsersUseCase {
    List<User> getActiveUsers();
}
