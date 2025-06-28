package v4.service;

import domain.Todo;
import v4.dto.TodoCreateRequestDto;
import v4.dto.TodoResponseDto;
import v4.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService
{
    private final TodoRepository repo;

    public TodoServiceImpl(TodoRepository repo)
    {
        this.repo = repo;
    }

    private TodoResponseDto convertToResponseDto(Todo todo)
    {
        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getCompleted(), todo.getCreatedAt(), todo.getUpdatedAt());
    }

    @Override
    public void createTodo(TodoCreateRequestDto dto)
    {
        Todo todo = new Todo(dto.getTitle());
        repo.save(todo);
    }

    @Override
    public TodoResponseDto getTodoById(Long id)
    {
        Todo todo = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 할 일을 찾을 수 없습니다"));

        return convertToResponseDto(todo);
    }

    @Override
    public List<TodoResponseDto> getAllTodos()
    {
        return repo.findAll().stream() // List<Todo>를 한 개씩 순서대로 처리
                .map(this::convertToResponseDto) // stream()으로 꺼낸 Todo 하나하나를 ConvertToResponseDto 메서드를 통해 TodoResponseDto로 변경
                .collect(Collectors.toList()); // TodoResponseDto 들을 List로 모음
    }

    @Override
    public TodoResponseDto deleteTodo(Long id)
    {
        Todo todo = repo.delete(id)
                .orElseThrow(() -> new NoSuchElementException("해당 할 일이 존재하지 않습니다."));

        return convertToResponseDto(todo);
    }

    @Override
    public boolean toggleCompleted(TodoCreateRequestDto dto)
    {
        Todo todo = repo.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 할 일이 존재하지 않습니다"));

        todo.updateCompleted();

        return todo.getCompleted();
    }
}
