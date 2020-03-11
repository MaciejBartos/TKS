package pl.lodz.p.edu.aggregates;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.edu.data.UsersEnt.UserEnt;
import pl.lodz.p.edu.prots.*;
import pl.lodz.p.edu.repositories.IRepoEnt;
import pl.lodz.p.edu.repositories.UserRepoEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryAdapter implements IAddItem<UserEnt>, IGetAllItems<UserEnt>, IGetItem<UserEnt>, ISortItems<UserEnt>, IUpdateItem<UserEnt> {

   @Autowired
   private IRepoEnt<UserEnt> userRepo;

    @Override
    public void add(UserEnt item) {
        userRepo.add(item);
    }

    @Override
    public List<UserEnt> getAll() {
        return userRepo.getAll();
    }

    @Override
    public Optional<UserEnt> getById(UUID id) {
        return userRepo.getById(id);
    }

    @Override
    public List<UserEnt> sort(String text) {
        return ((UserRepoEnt)userRepo).sort(text);
    }

    @Override
    public void update(UserEnt item) {
        userRepo.update(item);
    }
}
