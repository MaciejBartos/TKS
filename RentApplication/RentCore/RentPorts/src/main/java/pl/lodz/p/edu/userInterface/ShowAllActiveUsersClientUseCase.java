package pl.lodz.p.edu.userInterface;

import pl.lodz.p.edu.model.Clients.UserClient;

import java.util.List;

public interface ShowAllActiveUsersClientUseCase {
    List<UserClient> getActiveUsers();
}
