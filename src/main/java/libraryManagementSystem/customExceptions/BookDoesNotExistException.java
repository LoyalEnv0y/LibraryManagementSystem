package libraryManagementSystem.customExceptions;

public class BookDoesNotExistException extends RuntimeException {

    public BookDoesNotExistException(String message) {
        super(message);
    }
}
