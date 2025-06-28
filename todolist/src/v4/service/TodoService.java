package v4.service;

import v4.dto.TodoCreateRequestDto;
import v4.dto.TodoResponseDto;

import java.util.List;

public interface TodoService
{
    void createTodo(TodoCreateRequestDto dto);
    TodoResponseDto getTodoById(Long id);
    List<TodoResponseDto> getAllTodos();
    TodoResponseDto deleteTodo(Long id);
    boolean toggleCompleted(TodoCreateRequestDto dto);
}
