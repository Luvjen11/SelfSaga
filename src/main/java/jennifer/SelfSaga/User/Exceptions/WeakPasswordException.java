package jennifer.SelfSaga.User.Exceptions;

public class WeakPasswordException  extends RuntimeException{
    public WeakPasswordException(String message) {
        super(message);
    }
}
