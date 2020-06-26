package pl.lodz.p.edu.repositories;

import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.UsersEnt.UserClientEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserClientRepoEnt implements IRepoEnt<UserClientEnt> {

//    @Autowired
//    public UserRepo(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//
//    }

//    @PostConstruct
//    private void postConstruct(){
//        users.add(new Admin("Franek", UUID.randomUUID(), true,"admin", passwordEncoder.encode("admin")));
//        users.add(new Client("Kamil", UUID.randomUUID(), true,"client", passwordEncoder.encode("client")));
//        users.add(new ResourcesManager("Maciek", UUID.randomUUID(), true,"manager",passwordEncoder.encode("manager")));
//    }

    private List<UserClientEnt> users = new ArrayList<>();

    public void add(UserClientEnt u) throws Exception {
        synchronized (this) {
            users.add(u);
        }
    }

    public Optional<UserClientEnt> getById(UUID id) {
        return users.stream().filter(user -> user.getUserId().equals(id)).findFirst();
    }

    public Optional<UserClientEnt> getByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public List<UserClientEnt> getAll() {
        return users;
    }

    public void delete(UserClientEnt u) throws Exception {
        synchronized (this) {
            users.remove(u);
        }
    }

    public void update(UserClientEnt u) throws Exception {
        Optional<UserClientEnt> user = getById(u.getUserId());
        if (user.isPresent()) {
            synchronized (this) {
                user.get().setName(u.getName());
                user.get().setEmail(u.getEmail());
                user.get().setIsActive(u.getIsActive());
            }
        }
    }

    public List<UserClientEnt> sort(String text){
        List<UserClientEnt> sortUsers = new ArrayList<>();
        for (UserClientEnt u: users
        ) {
            if (u.getName().length() >= text.length()) {
                if (u.getName().substring(0, text.length()).equals(text)) {
                    sortUsers.add(u);
                }
            }

        }
        return sortUsers;
    }
}
