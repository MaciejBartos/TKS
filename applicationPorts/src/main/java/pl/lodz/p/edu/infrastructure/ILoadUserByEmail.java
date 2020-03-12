package pl.lodz.p.edu.infrastructure;

import pl.lodz.p.edu.model.Users.User;

import java.util.Optional;

public interface ILoadUserByEmail {
    Optional<User> getByEmail(String email);
}
