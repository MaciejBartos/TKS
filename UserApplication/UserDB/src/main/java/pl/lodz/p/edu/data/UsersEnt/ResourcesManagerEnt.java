package pl.lodz.p.edu.data.UsersEnt;

import java.util.UUID;

public class ResourcesManagerEnt extends UserEnt {
    public ResourcesManagerEnt(String name, UUID id, boolean isActive, String email, String password) {
        super(name, "ResourcesManager", id, isActive, email, password);
    }
}
