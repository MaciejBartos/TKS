package pl.lodz.p.edu.userInterface;

import java.util.List;

public interface SortItemsUseCase<T> {
   List<T> sort(String text);
}
