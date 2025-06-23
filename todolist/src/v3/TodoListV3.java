package v3;

import domain.Todo;
import v3.dto.CreateRequestDto;
import v3.dto.TodoResponseDto;
import v3.dto.UpdateRequestDto;
import v3.repository.TodoMemoryRepository;
import v3.repository.TodoRepository;
import v3.service.TodoService;
import v3.service.TodoServiceImpl;

import java.util.List;
import java.util.Scanner;

public class TodoListV3
{
    private final TodoService service;
    private final Scanner sc = new Scanner(System.in);

    public TodoListV3(TodoService service)
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

    private void create()
    {
        String title = inputLine("할 일 입력: ");
        String answer = inputLine("할 일 [" + title + "] 등록 하시겠습니까? [y/n]");

        if(answer.equalsIgnoreCase("y"))
        {
            CreateRequestDto dto = new CreateRequestDto(title);
            service.save(dto);
            System.out.println("등록 완료");
        }
        else
        {
            System.out.println("등록 취소");
        }
    }

    private void updateStatus()
    {
        Long id = inputLong("상태 변경할 ID: ");
        String answer = inputLine("ID [" + id + "]의 상태를 변경하시겠습니까? [y/n]");

        if(answer.equalsIgnoreCase("y"))
        {
            UpdateRequestDto dto = new UpdateRequestDto(id);
            service.toggleComplete(dto);
            System.out.println("상태 변경 완료");
        }
        else
        {
            System.out.println("상태 변경 취소");
        }
    }

    private void readOne()
    {
        Long id = inputLong("조회할 ID: ");
        TodoResponseDto dto = service.findById(id);
        System.out.println(dto);
    }

    private void readAll()
    {
        List<TodoResponseDto> dto = service.readAll();
        if(dto.isEmpty())
        {
            System.out.println("할 일이 존재하지 않습니다.");
        }
        else
        {
            dto.forEach(System.out::println);
        }
    }

    private void delete()
    {
        Long id = inputLong("삭제할 ID: ");
        TodoResponseDto dto = service.findById(id);
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
                switch (choice)
                {
                    case 1 -> create();
                    case 2 -> readOne();
                    case 3 -> updateStatus();
                    case 4 -> readAll();
                    case 5 -> delete();
                    case 0 ->
                    {
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

    public static void main(String[] args)
    {
        TodoRepository repo = new TodoMemoryRepository();
        TodoService service = new TodoServiceImpl(repo);
        TodoListV3 list = new TodoListV3(service);
        list.run();
    }
}
