package v4;

import v4.repository.TodoMemoryRepository;
import v4.repository.TodoRepository;
import v4.service.TodoService;
import v4.service.TodoServiceImpl;

public class AppConfig
{
    public TodoRepository todoRepository()
    {
        return new TodoMemoryRepository();
    }

    public TodoService todoService()
    {
        return new TodoServiceImpl(todoRepository());
    }
}
