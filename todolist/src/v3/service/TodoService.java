package v3.service;

import v3.dto.CreateRequestDto;
import v3.dto.TodoResponseDto;
import v3.dto.UpdateRequestDto;

import java.util.List;

public interface TodoService
{
    void save(CreateRequestDto dto);
    TodoResponseDto findById(Long id);
    List<TodoResponseDto> readAll();
    TodoResponseDto delete(Long id);
    boolean toggleComplete(UpdateRequestDto dto);
}