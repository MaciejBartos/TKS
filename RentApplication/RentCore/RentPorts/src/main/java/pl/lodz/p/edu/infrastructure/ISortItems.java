package pl.lodz.p.edu.infrastructure;

import java.util.List;

public interface ISortItems<T> {
    List<T> sort(String text);
}
