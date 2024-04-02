package africa.semicolon.chichidiary.data.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Diary {

    @Id
    private String id;
    private String userName;
    private String passWord;
    private boolean isLocked;


}
