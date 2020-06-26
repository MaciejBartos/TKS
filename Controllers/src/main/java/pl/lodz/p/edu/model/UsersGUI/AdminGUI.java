package pl.lodz.p.edu.model.UsersGUI;

import java.util.UUID;

public class AdminGUI extends UserGUI {

    public AdminGUI(String name, UUID id, boolean isActive, String email, String password) {
        super(name, "Admin", id, isActive, email, password);
    }
}
