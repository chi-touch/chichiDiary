package africa.semicolon.chichidiary.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Entry {
    @Id
    private String id;
    private String author;
    private String body;
    private String title;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
