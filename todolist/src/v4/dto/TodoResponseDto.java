package v4.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TodoResponseDto
{
    private Long id;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TodoResponseDto(Long id, String title, boolean completed, LocalDateTime createdAt, LocalDateTime updatedAt)
    {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String toString()
    {
        return "[" + id + "]" +
                (completed ? "[완료]" : "[미완료]") +
                title +
                "\n- 생성일: " + createdAt.format(formatter) +
                "\n- 수정일: " + (updatedAt != null ? updatedAt.format(formatter) : "-");
    }
}
