package v3.dto;

public class UpdateRequestDto
{
    private Long id;
    private boolean completed;

    public Long getId()
    {
        return id;
    }

    public boolean getCompleted()
    {
        return completed;
    }

    public UpdateRequestDto(Long id)
    {
        this.id = id;
    }
}
