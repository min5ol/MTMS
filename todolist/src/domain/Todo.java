package domain;

import java.time.LocalDateTime;

public class Todo {
    private static Long idCounter = 1L;

    private Long id;
    private String title;
    private Boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Todo(String title) {
        this.id = idCounter++;
        this.title = title;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Boolean getCompleted() { return completed; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateCompleted() {
        this.completed = !this.completed;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[" + id + "]" + (completed ? "[완료]" : "[미완료]") + title +
                " (생성: " + createdAt + " 수정: " + updatedAt + ")";
    }
}
