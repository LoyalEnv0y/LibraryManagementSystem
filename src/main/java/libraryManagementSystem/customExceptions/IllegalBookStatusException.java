package libraryManagementSystem.customExceptions;

public class IllegalBookStatusException extends RuntimeException {
    public IllegalBookStatusException(String message) {
        super(message);
    }
}
