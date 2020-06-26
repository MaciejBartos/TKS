package pl.lodz.p.edu.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.UsersEnt.AdminEnt;
import pl.lodz.p.edu.data.UsersEnt.ClientEnt;
import pl.lodz.p.edu.data.UsersEnt.ResourcesManagerEnt;
import pl.lodz.p.edu.data.UsersEnt.UserEnt;
import pl.lodz.p.edu.model.Users.Admin;
import pl.lodz.p.edu.model.Users.Client;
import pl.lodz.p.edu.model.Users.ResourcesManager;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepoEnt implements IUserRepoEnt<UserEnt> {

    private List<UserEnt> users = new ArrayList<>();
   // private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    @Autowired
//    public UserRepoEnt(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @PostConstruct
    private void postConstruct(){
//        users.add(new AdminEnt("Franek", UUID.randomUUID(), true,"admin", passwordEncoder.encode("admin")));
//        users.add(new ClientEnt("Kamil", UUID.randomUUID(), true,"client", passwordEncoder.encode("client")));
//        users.add(new ResourcesManagerEnt("Maciek", UUID.randomUUID(), true,"manager",passwordEncoder.encode("manager")));
    }

    public void add(UserEnt u) throws Exception {
        synchronized (this) {
            users.add(u);
        }
    }

    public Optional<UserEnt> getById(UUID id) {
        return users.stream().filter(user -> user.getUserId().equals(id)).findFirst();
    }

    public Optional<UserEnt> getByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public List<UserEnt> getAll() {
        return users;
    }

    public void delete(UserEnt u) throws Exception {
        synchronized (this) {
            users.remove(u);
        }
    }

    public void update(UserEnt u) throws Exception {
        Optional<UserEnt> user = getById(u.getUserId());
        if (user.isPresent()) {
            synchronized (this) {
                user.get().setName(u.getName());
                user.get().setEmail(u.getEmail());
                user.get().setPassword(u.getPassword());
                user.get().setIsActive(u.getIsActive());
            }
        }
    }

    public List<UserEnt> sort(String text){
        List<UserEnt> sortUsers = new ArrayList<>();
        for (UserEnt u:
                users) {
            if (u.getName().length() >= text.length()) {
                if (u.getName().substring(0, text.length()).equals(text)) {
                    sortUsers.add(u);
                }
            }

        }
        return sortUsers;
    }
}
