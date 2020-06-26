package pl.lodz.p.edu.repositories;


import pl.lodz.p.edu.data.UsersEnt.UserEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepoEnt<T> {

    void add(T t) throws Exception;

    Optional<T> getById(UUID id);

    List<T> getAll();

    void update(T t) throws Exception;
    
    void delete(T item) throws Exception;

    List<UserEnt> sort(String text);

}
