package v2;

import domain.Todo;
import java.util.List;
import java.util.Scanner;

public class TodoListV2
{
    private final TodoService service;
    private final Scanner sc = new Scanner(System.in);

    public TodoListV2(TodoService service)
    {
        this.service = service;
    }

    // 보조 메서드: 사용자 입력 → 공백 제거 + 숫자 변환
    private int inputInt(String message)
    {
        System.out.print(message);
        return Integer.parseInt(sc.nextLine().trim());
    }

    private Long inputLong(String message)
    {
        System.out.print(message);
        return Long.parseLong(sc.nextLine().trim());
    }

    private String inputLine(String message)
    {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    private void create()
    {
        String title = inputLine("할 일 입력: ");
        String answer = inputLine("할 일 [" + title + "] 등록 하시겠습니까? [y/n]");

        if(answer.equalsIgnoreCase("y"))
        {
            service.save(title);
            System.out.println("등록 완료");
        }
        else
        {
            System.out.println("등록 취소");
        }
    }

    private void readOne()
    {
        Long id = inputLong("조회할 ID: ");
        Todo todo = service.findById(id);
        System.out.println(todo);
    }

    private void updateStatus()
    {
        Long id = inputLong("상태 변경할 ID: ");
        String answer = inputLine("ID [" + id + "]의 상태를 변경하시겠습니까? [y/n]");

        if(answer.equalsIgnoreCase("y"))
        {
            service.updateStatus(id);
            System.out.println("상태 변경 완료");
        }
        else
        {
            System.out.println("상태 변경 취소");
        }
    }

    private void readAll()
    {
        List<Todo> todos = service.findAll();
        if(todos.isEmpty())
        {
            System.out.println("할 일이 존재하지 않습니다.");
        }
        else
        {
            todos.forEach(System.out::println);
        }
    }

    private void delete()
    {
        Long id = inputLong("삭제할 ID: ");
        Todo todo = service.findById(id);
        String answer = inputLine("ID [" + id + "]의 할 일을 삭제하시겠습니까? [y/n]");

        if(answer.equalsIgnoreCase("y"))
        {
            service.delete(id);
            System.out.println("삭제 완료");
        }
        else
        {
            System.out.println("삭제 취소");
        }
    }

    public void run()
    {
        while(true)
        {
            printMenu();
            int choice = inputInt("메뉴 번호 선택 > ");

            try
            {
                switch (choice)
                {
                    case 1 -> create();
                    case 2 -> readOne();
                    case 3 -> updateStatus();
                    case 4 -> readAll();
                    case 5 -> delete();
                    case 0 -> {
                        System.out.println("프로그램 종료");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다.");
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("[오류] " + e.getMessage());
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

    public static void main(String[] args)
    {
        TodoRepository repo = new TodoRepository();
        TodoService service = new TodoService(repo);
        TodoListV2 list = new TodoListV2(service);
        list.run();
    }
}
