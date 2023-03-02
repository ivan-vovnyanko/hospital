package hospital.service;

import java.util.List;

public interface GenericService<T> {
    T create(T element);

    T get(Long id);

    T update(T element);

    List<T> getAll();

    boolean delete(Long id);
}
