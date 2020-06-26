package pl.lodz.p.edu.userInterface;

import pl.lodz.p.edu.model.Users.User;

public interface ShowUserByNameUseCase {
    User get(String name);
}
