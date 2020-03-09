package pl.lodz.p.edu.repositories;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IRepoEnt<T> {

    void add(T t);

    Optional<T> getById(UUID id);

    List<T> getAll();

    void delete(T t);

    void update(T t);

}
