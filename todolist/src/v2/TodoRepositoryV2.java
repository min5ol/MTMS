package v2;

import domain.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepositoryV2
{
    // List는 인터페이스, ArrayList는 구현체
    // 유연한 코드 작성을 위해 List로 선언, 순차적 저장에 적합한 ArrayList로 초기화
    private final List<Todo> list = new ArrayList<>();

    // 저장소에 투두 객체 저장
    public void save(Todo todo)
    {
        list.add(todo);
    }

    // ID로 투두 조회. 못 찾으면 null 반환
    // SRP 원칙에 따라 Repository는 조회만 담당하고 예외는 던지지 않음
    public Todo findById(Long id)
    {
        return list.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 전체 투두 목록 반환 (방어적 복사로 캡슐화 유지)
    public List<Todo> findAll()
    {
        return new ArrayList<>(list);
    }

    // 투두 객체를 전달받아 삭제 (삭제 판단은 외부에서 수행)
    public void delete(Todo todo)
    {
        list.remove(todo);
    }
}
