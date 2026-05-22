package yoot.project_demo.dto.room;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomUpsertRequest {

    @Length(min = 1, max = 20)
    private String room_code;

    @Length(max = 100)
    private String name;

    @Min(1)
    private int capacity;

    private String description;
}
