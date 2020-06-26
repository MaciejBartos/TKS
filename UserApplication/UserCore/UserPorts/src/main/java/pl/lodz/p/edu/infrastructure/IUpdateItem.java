package pl.lodz.p.edu.infrastructure;

public interface IUpdateItem<T> {
    void update(T item) throws Exception;
}
