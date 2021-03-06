package pl.lodz.p.edu.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.converters.FromDomainConverter;
import pl.lodz.p.edu.converters.ToDomainConverter;
import pl.lodz.p.edu.data.UsersEnt.UserEnt;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Users.User;
import pl.lodz.p.edu.repositories.IUserRepoEnt;
import pl.lodz.p.edu.repositories.UserRepoEnt;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements IAddItem<User>, IGetAllItems<User>, IGetItem<User>, ISortItems<User>, IUpdateItem<User>, ILoadUserByEmail, IDeleteItem<User> {

    @Autowired
    private IUserRepoEnt<UserEnt> userRepo;

    @Override
    public void add(User item) throws Exception {
        userRepo.add(FromDomainConverter.convertUser(item));
    }

    @Override
    public List<User> getAll() {
        List<User> users = new LinkedList<>();
        for (UserEnt user : userRepo.getAll()) {
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
        for (UserEnt user : ((UserRepoEnt) userRepo).sort(text)) {
            users.add(ToDomainConverter.convertUser(user));
        }
        return users;
    }

    @Override
    public void update(User item) throws Exception {
        userRepo.update(FromDomainConverter.convertUser(item));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return Optional.of(ToDomainConverter.convertUser(((UserRepoEnt) userRepo).getByEmail(email).get()));
    }

    @Override
    public void delete(User item) throws Exception {
        userRepo.delete(FromDomainConverter.convertUser(item));
    }
}
