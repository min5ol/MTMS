package v4.repository;

import domain.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoMemoryRepository implements TodoRepository
{
    private final List<Todo> list = new ArrayList<>();

    public Todo save(Todo todo)
    {
        list.add(todo);
        return todo;
    }

    public Optional<Todo> findById(Long id)
    {
        return list.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    public List<Todo> findAll()
    {
        return new ArrayList<>(list);
    }

    public Optional<Todo> delete(Long id)
    {
        Optional<Todo> target = findById(id);
        target.ifPresent(list::remove);

        return target;
    }
}
