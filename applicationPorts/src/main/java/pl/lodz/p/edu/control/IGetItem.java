package pl.lodz.p.edu.control;

import java.util.Optional;
import java.util.UUID;

public interface IGetItem<T> {
    Optional<T> getById(UUID id);
}
