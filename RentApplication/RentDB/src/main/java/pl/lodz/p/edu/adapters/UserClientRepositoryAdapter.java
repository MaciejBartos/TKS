package pl.lodz.p.edu.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.data.UsersEnt.UserClientEnt;
import pl.lodz.p.edu.infrastructure.*;
import pl.lodz.p.edu.model.Clients.UserClient;
import pl.lodz.p.edu.repositories.IRepoEnt;
import pl.lodz.p.edu.repositories.UserClientRepoEnt;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserClientRepositoryAdapter implements IAddItem<UserClient>, IGetAllItems<UserClient>, IGetItem<UserClient>, ISortItems<UserClient>, IUpdateItem<UserClient>, ILoadUserClientByEmail, IDeleteItem<UserClient> {

   @Autowired
   private IRepoEnt<UserClientEnt> userRepo;

    @Override
    public void add(UserClient item) throws Exception {
        userRepo.add(FromDomainConverter.convertUser(item));
    }

    @Override
    public List<UserClient> getAll() {
        List<UserClient> users = new LinkedList<>();
        for(UserClientEnt user: userRepo.getAll()){
            users.add(ToDomainConverter.convertUser(user));
        }
        return users;
    }

    @Override
    public Optional<UserClient> getById(UUID id) {
        return Optional.of(ToDomainConverter.convertUser(userRepo.getById(id).get()));
    }

    @Override
    public List<UserClient> sort(String text) {
        List<UserClient> users = new LinkedList<>();
        for(UserClientEnt user: ((UserClientRepoEnt)userRepo).sort(text)){
            users.add(ToDomainConverter.convertUser(user));
        }
        return users;
    }

    @Override
    public void update(UserClient item) throws Exception {
        userRepo.update(FromDomainConverter.convertUser(item));
    }

    @Override
    public Optional<UserClient> getByEmail(String email) {
        return Optional.of(ToDomainConverter.convertUser(((UserClientRepoEnt)userRepo).getByEmail(email).get()));
    }

    @Override
    public void delete(UserClient item) throws Exception {
        userRepo.delete(FromDomainConverter.convertUser(item));
    }
}
