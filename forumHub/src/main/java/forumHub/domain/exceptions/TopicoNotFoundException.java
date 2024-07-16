package forumHub.domain.exceptions;

public class TopicoNotFoundException extends RuntimeException{
    public TopicoNotFoundException(String message) {
        super(message);
    }
}
