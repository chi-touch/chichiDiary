package africa.semicolon.chichidiary.exceptions;

public class TitleAlreadyExistException extends RuntimeException{
    public TitleAlreadyExistException(String message){
        super(message);
    }
}
