package africa.semicolon.chichidiary.dto.request;

import lombok.Data;

@Data
public class CreateEntryRequest {
    private String title;
    private String author;
    private String body;
}
