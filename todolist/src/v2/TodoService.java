package v2;

import domain.Todo;

import java.util.List;

public class TodoService
{
    private final TodoRepository repo;

    // 생성자 주입을 통해 repo 구현체와의 결합도 낮춤
    public TodoService(TodoRepository repo)
    {
        this.repo = repo;
    }

    public void save(String title)
    {
        Todo todo = new Todo(title);
        repo.save(todo);
    }

    public Todo findById(Long id)
    {
        Todo todo = repo.findById(id);
        throwIfTodoNotFound(id,todo);
        return todo;
    }

    public List<Todo> findAll()
    {
        return repo.findAll();
    }

    public void delete(Long id)
    {
        Todo todo = repo.findById(id);
        throwIfTodoNotFound(id,todo);
        repo.delete(todo);
    }

    public void updateStatus(Long id)
    {
        Todo todo = findById(id); // 예외 처리
        todo.updateCompleted();
    }

    // id로 투두를 찾을 수 없는 경우 예외 발생
    private void throwIfTodoNotFound(Long id, Todo todo)
    {
        if(todo == null)
        {
            throw new IllegalArgumentException("입력한 ID [" + id + "]를 찾을 수 없습니다.");
        }
    }
}