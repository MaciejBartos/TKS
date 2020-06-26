package pl.lodz.p.edu.model.UsersGUI;

import java.util.UUID;

public class ClientGUI extends UserGUI {

    public ClientGUI(String name, UUID id, boolean isActive, String email, String password) {
        super(name, "Client", id, isActive, email, password);
    }
}
