package africa.semicolon.chichidiary.exceptions;

public class UserNameAlreadyExistException extends RuntimeException{
    public UserNameAlreadyExistException(String message){
        super(message);
    }
}
