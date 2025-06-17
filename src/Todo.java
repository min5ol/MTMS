import java.time.LocalDateTime;

public class Todo
{
    private Long id; // 고유 식별자
    private String title; // 할 일
    private Boolean completed; // 완료 여부
    private LocalDateTime createdAt; // 생성 시각
    private LocalDateTime updatedAt; // 마지막 수정 시각

    // 생성자
    public Todo(Long id, String title)
    {
        this.id = id;
        this.title = title;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // getter
    public Long getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public Boolean getCompleted()
    {
        return completed;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt()
    {
        return updatedAt;
    }

    // 제목 수정 + 시각
    public void setTitle(String title)
    {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    // 완료 여부 전환 메서드 true <-> false
    public void updateCompleted()
    {
        this.completed = !this.completed;
        this.updatedAt = LocalDateTime.now();
    }

    // toString
    @Override
    public String toString()
    {
        return "[" + id + "]" + (completed ? "[완료]" : "[미완료]") + title + "(생성 : " + createdAt + " 수정 : " + updatedAt + ")";
    }
}
