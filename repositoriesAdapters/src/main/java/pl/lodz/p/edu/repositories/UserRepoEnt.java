package pl.lodz.p.edu.repositories;

import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.UsersEnt.UserEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepoEnt implements IRepoEnt<UserEnt> {

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

    private List<UserEnt> users = new ArrayList<>();
//    private PasswordEncoder passwordEncoder;

    public void add(UserEnt u) {
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

    public void delete(UserEnt u) {
        synchronized (this) {
            users.remove(u);
        }
    }

    public void update(UserEnt u) {
        Optional<UserEnt> user = getById(u.getUserId());
        if (user.isPresent()) {
//            users.set(users.indexOf(user), u);
            synchronized (this) {
                user.get().setName(u.getName());
                user.get().setEmail(u.getEmail());
                user.get().setPassword(u.getPassword());
            }
        }
    }

    public List<UserEnt> sort(String text){
        List<UserEnt> sortUsers = new ArrayList<>();
        for (UserEnt u: users
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
