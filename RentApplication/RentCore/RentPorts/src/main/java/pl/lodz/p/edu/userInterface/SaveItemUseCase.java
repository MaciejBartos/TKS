package pl.lodz.p.edu.userInterface;

public interface SaveItemUseCase<T> {
    void add(T item) throws Exception;
}
