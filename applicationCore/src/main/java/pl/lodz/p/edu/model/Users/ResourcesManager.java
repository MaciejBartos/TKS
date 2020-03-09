package pl.lodz.p.edu.model.Users;

import java.util.UUID;

public class ResourcesManager extends User {
    public ResourcesManager(String name, UUID id, boolean isActive) {
        super(name, "ResourcesManager", id, isActive);
    }
}
