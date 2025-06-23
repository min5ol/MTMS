package v3.service;

import domain.Todo;
import v3.dto.CreateRequestDto;
import v3.dto.TodoResponseDto;
import v3.dto.UpdateRequestDto;
import v3.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    public void save(CreateRequestDto dto)
    {
        Todo todo = new Todo(dto.getTitle());
        repo.save(todo);
    }

    @Override
    public TodoResponseDto findById(Long id)
    {
        Todo todo = repo.findById(id)
                .orElseThrow(()-> new NoSuchElementException("해당 할 일을 찾을 수 없습니다."));
        return convertToResponseDto(todo);
    }

    @Override
    public List<TodoResponseDto> readAll()
    {
       List<Todo> todos = repo.findAll();
       List<TodoResponseDto> result = new ArrayList<>();

       for(Todo todo : todos)
       {
           result.add(convertToResponseDto(todo));
       }

       return result;
    }

    @Override
    public TodoResponseDto delete(Long id)
    {
        Todo todo = repo.delete(id)
                .orElseThrow(()-> new NoSuchElementException("해당 할 일을 찾을 수 없습니다."));
        return convertToResponseDto(todo);
    }

    @Override
    public boolean toggleComplete(UpdateRequestDto dto)
    {
        Todo todo = repo.findById(dto.getId())
                .orElseThrow(()-> new NoSuchElementException("해당 할 일을 찾을 수 없습니다."));
        todo.updateCompleted();
        return todo.getCompleted();
    }
}
