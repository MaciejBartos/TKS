package pl.lodz.p.edu.infrastructure;

import pl.lodz.p.edu.model.Clients.UserClient;

import java.util.Optional;

public interface ILoadUserClientByEmail {
    Optional<UserClient> getByEmail(String email);
}
