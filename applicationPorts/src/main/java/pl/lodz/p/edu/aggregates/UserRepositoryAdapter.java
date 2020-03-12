package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.edu.data.UsersEnt.UserEnt;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Users.User;
import pl.lodz.p.edu.repositories.IRepoEnt;
import pl.lodz.p.edu.repositories.UserRepoEnt;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryAdapter implements IAddItem<User>, IGetAllItems<User>, IGetItem<User>, ISortItems<User>, IUpdateItem<User> {

   @Autowired
   private IRepoEnt<UserEnt> userRepo;

    @Override
    public void add(User item) {
        userRepo.add(FromDomainConverter.convertUser(item));
    }

    @Override
    public List<User> getAll() {
        List<User> users = new LinkedList<>();
        for(UserEnt user: userRepo.getAll()){
            users.add(ToDomainConverter.convertUser(user));
        }
        return users;
    }

    @Override
    public Optional<User> getById(UUID id) {
        return Optional.of(ToDomainConverter.convertUser(userRepo.getById(id).get()));
    }

    @Override
    public List<User> sort(String text) {
        List<User> users = new LinkedList<>();
        for(UserEnt user: ((UserRepoEnt)userRepo).sort(text)){
            users.add(ToDomainConverter.convertUser(user));
        }
        return users;
    }

    @Override
    public void update(User item) {
        userRepo.update(FromDomainConverter.convertUser(item));
    }
}
