package v3.dto;

public class CreateRequestDto
{
    private String title;

    public String getTitle()
    {
        return title;
    }

    public CreateRequestDto(String title)
    {
        this.title = title;
    }
}
