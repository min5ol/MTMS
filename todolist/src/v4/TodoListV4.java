package v4;

import v4.dto.TodoCreateRequestDto;
import v4.dto.TodoResponseDto;
import v4.service.TodoService;

import java.util.List;
import java.util.Scanner;

public class TodoListV4
{
    private final TodoService service;
    private final Scanner sc = new Scanner(System.in);

    public TodoListV4(TodoService service)
    {
        this.service = service;
    }

    private int inputInt(String msg)
    {
        System.out.println(msg);
        return Integer.parseInt(sc.nextLine().trim());
    }

    private Long inputLong(String msg)
    {
        System.out.println(msg);
        return Long.parseLong(sc.nextLine().trim());
    }

    private String inputLine(String msg)
    {
        System.out.println(msg);
        return sc.nextLine().trim();
    }

    private boolean askYesOrNo(String msg)
    {
        while(true)
        {
            String answer = inputLine(msg).toLowerCase();
            if(answer.equals("y"))
            {
                return true;
            }

            if(answer.equals("n"))
            {
                return false;
            }

            System.out.println("잘못된 입력입니다. [y/n]만 입력하세요.");
        }
    }

    private void createTodo()
    {
        String title = inputLine("할 일 입력");

        while(true)
        {
            if(askYesOrNo("할 일 [" + title + "] 등록하시겠습니까? [y/n]"))
            {
                TodoCreateRequestDto dto = new TodoCreateRequestDto(title);
                service.createTodo(dto);
                System.out.println("등록 완료");
                return;
            }
            else
            {
                if(askYesOrNo("정말 등록을 취소하시겠습니까? [y/n]"))
                {
                    System.out.println("등록이 취소되었습니다.");
                    return;
                }
            }
        }
    }

    private void updateTodoStatus()
    {
        Long id = inputLong("상태를 변경 할 ID 입력: ");

        while(true)
        {
            if(askYesOrNo("ID [" + id + "]의 상태를 변경하시겠습니까? [y/n]"))
            {
                TodoCreateRequestDto dto = new TodoCreateRequestDto(id);
                service.toggleCompleted(dto);
                System.out.println("상태 변경 완료");
                return;
            }
            else
            {
                if(askYesOrNo("정말 변경을 취소하시겠습니까? [y/n]"))
                {
                    System.out.println("상태 변경이 취소되었습니다.");
                    return;
                }
            }
        }
    }

    private void readTodo()
    {
        Long id = inputLong("조회할 할 일의 ID 입력: ");
        TodoResponseDto dto = service.getTodoById(id);
        System.out.println(dto);
    }

    private void readAllTodos()
    {
        List<TodoResponseDto> todos = service.getAllTodos();
        if(todos.isEmpty())
        {
            System.out.println("할 일이 존재하지 않습니다.");
        }
        else
        {
            todos.forEach(System.out::println);
        }
    }

    private void deleteTodo()
    {
        Long id = inputLong("삭제할 할 일의 ID 입력: ");
        TodoResponseDto dto = service.getTodoById(id);

        while(true)
        {
            if(askYesOrNo("ID [" + id + "]의 할 일을 삭제하시겠습니까? [y/n]"))
            {
                service.deleteTodo(id);
                System.out.println("삭제 완료");
                return;
            }
            else
            {
                if(askYesOrNo("정말 삭제를 취소하시겠습니까? [y/n]"))
                {
                    System.out.println("삭제가 취소되었습니다.");
                    return;
                }
            }
        }
    }

    public void printMenu()
    {
        System.out.println("\n ===== TODO List =====");
        System.out.println("1. 할 일 등록");
        System.out.println("2. 할 일 조회");
        System.out.println("3. 상태 변경");
        System.out.println("4. 전체 목록 조회");
        System.out.println("5. 삭제");
        System.out.println("0. 종료");
    }

    public void run()
    {
        while(true)
        {
            printMenu();
            int choice = inputInt("메뉴 번호 선택 > ");

            try
            {
                switch(choice)
                {
                    case 1 -> createTodo();
                    case 2 -> readTodo();
                    case 3 -> updateTodoStatus();
                    case 4 -> readAllTodos();
                    case 5 -> deleteTodo();
                    case 0 -> {
                        System.out.println("Todo List 종료");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다.");
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("[오류]" + e.getMessage());
            }
        }
    }

    public static void main(String[] args)
    {
        AppConfig appConfig = new AppConfig();
        TodoService service = appConfig.todoService();
        TodoListV4 app = new TodoListV4(service);

        app.run();
    }
}
