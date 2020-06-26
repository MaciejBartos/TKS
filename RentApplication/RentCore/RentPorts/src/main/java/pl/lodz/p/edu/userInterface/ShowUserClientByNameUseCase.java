package pl.lodz.p.edu.userInterface;

import pl.lodz.p.edu.model.Clients.UserClient;


public interface ShowUserClientByNameUseCase {
    UserClient get(String name);
}
