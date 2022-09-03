package libraryManagementSystem.customExceptions;

public class IllegalPermissionException extends RuntimeException{
    public IllegalPermissionException(String message) {
        super(message);
    }
}
