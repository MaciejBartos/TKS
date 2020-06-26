package pl.lodz.p.edu.userInterface;

import java.util.List;

public interface ShowAllItemsUseCase<T> {
    List<T> getAll();
}
