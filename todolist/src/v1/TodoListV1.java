package v1;

import domain.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoListV1
{
    private ArrayList<Todo> todos = new ArrayList<>();

    // 추가
    public void create(String title)
    {
        todos.add(new Todo(title));
    }

    // id를 통해 할일 찾기, null 허용
    public Todo findById(Long id)
    {
        for(Todo todo : todos)
        {
            if(todo.getId().equals(id))
            {
                return todo;
            }
        }
        return null;
    }

    // ID를 통한 완료 여부 변환
    public void todoStatus(Long id)
    {
        Todo todo = findById(id);
        if(todo != null)
        {
            todo.updateCompleted();
            System.out.println("완료 여부 변경");
        }
        else
        {
            System.out.println("해당 ID의 할 일 존재하지 않음");
        }
    }

    // 전체 할 일 출력
    public void readAll()
    {
        if(todos.isEmpty())
        {
            System.out.println("할 일이 없습니다.");
            return;
        }
        for(Todo todo : todos)
        {
            System.out.println(todo);
        }
    }

    // 하나의 할 일 출력
    public void readOne(Long id)
    {
        Todo todo = findById(id);
        if(todo != null)
        {
            System.out.println(todo);
        }
        else
        {
            System.out.println("해당 ID의 할 일 존재하지 않음");
        }
    }

    // 삭제
    public void delete(Long id)
    {
        Todo todo = findById(id);
        if(todo != null)
        {
            todos.remove(todo);
            System.out.println("할 일이 삭제되었습니다.");
        }
        else
        {
            System.out.println("해당 ID의 할 일 존재하지 않음");
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        TodoListV1 todoList = new TodoListV1();

        while(true)
        {
            System.out.println("======TODO LIST=======");
            System.out.println("1. 할 일 등록");
            System.out.println("2. 할 일 확인");
            System.out.println("3. 할 일 상태 변경");
            System.out.println("4. 할 일 전체 확인");
            System.out.println("5. 할 일 삭제");
            System.out.println("0. 종료");
            System.out.println("원하는 서비스 선택 >");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 0)
            {
                System.out.println("투두 리스트를 종료합니다.");
                break;
            }

            switch(choice)
            {
                case 1:
                    System.out.println("할 일을 입력: ");
                    String title = sc.nextLine();
                    todoList.create(title);
                    System.out.println("할 일 등록 완료");
                    break;

                case 2:
                    System.out.println("조회 할 ID 입력: ");
                    Long readId = sc.nextLong();
                    todoList.readOne(readId);
                    break;

                case 3:
                    System.out.println("상태 변경할 ID 입력: ");
                    Long statusId = sc.nextLong();
                    todoList.todoStatus(statusId);
                    break;

                case 4:
                    todoList.readAll();
                    break;

                case 5:
                    System.out.println("삭제할 ID 입력: ");
                    Long deleteId = sc.nextLong();
                    todoList.delete(deleteId);
                    break;

                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            }
        }

        sc.close();
    }
}
