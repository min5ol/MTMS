import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList
{
    ArrayList<Todo> todos = new ArrayList<>();
    Long idCounter = 1L;

    public void add(String title)
    {
        Todo todo = new Todo();
        todo.setId(idcounter++);
        todo.setTitle(title);
        todo.setCompleted(false);
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());
        todos.add(todo);
    }

    public void setStatus()

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
    }
}
