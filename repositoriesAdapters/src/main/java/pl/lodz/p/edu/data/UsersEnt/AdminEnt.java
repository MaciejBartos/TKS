package pl.lodz.p.edu.data.UsersEnt;

import java.util.UUID;

public class AdminEnt extends UserEnt {
    public AdminEnt(String name, UUID id, boolean isActive, String email, String password) {
        super(name, "AdminEnt", id, isActive, email, password);
    }
}
