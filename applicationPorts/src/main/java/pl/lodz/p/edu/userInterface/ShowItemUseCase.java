package pl.lodz.p.edu.userInterface;

import java.util.UUID;

public interface ShowItemUseCase<T> {
    T get(UUID id);
}
