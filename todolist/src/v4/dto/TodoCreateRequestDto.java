package v4.dto;

public class TodoCreateRequestDto
{
    private Long id;
    private String title;

    public Long getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public TodoCreateRequestDto(String title)
    {
        this.title = title;
    }

    public TodoCreateRequestDto(Long id)
    {
        this.id = id;
    }

    public TodoCreateRequestDto(Long id, String title)
    {
        this.id = id;
        this.title = title;
    }
}
