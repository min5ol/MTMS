package v3.repository;

import domain.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository
{
    Todo save(Todo todo);
    Optional<Todo> findById(Long id);
    List<Todo> findAll();
    Optional<Todo> delete(Long id);
}
