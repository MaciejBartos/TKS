package pl.lodz.p.edu.model.Users;

import java.util.UUID;

public class Client extends User {
    public Client(String name, UUID id, boolean isActive) {
        super(name, "Client", id, isActive);
    }
}
