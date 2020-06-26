package pl.lodz.p.edu.data.UsersEnt;

import java.util.UUID;

public class ClientEnt extends UserEnt {
    public ClientEnt(String name, UUID id, boolean isActive, String email, String password) {
        super(name, "Client", id, isActive, email, password);
    }
}
