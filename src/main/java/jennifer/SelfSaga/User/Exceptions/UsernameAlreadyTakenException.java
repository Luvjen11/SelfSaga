package jennifer.SelfSaga.User.Exceptions;

public class UsernameAlreadyTakenException extends RuntimeException{

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }   
}
