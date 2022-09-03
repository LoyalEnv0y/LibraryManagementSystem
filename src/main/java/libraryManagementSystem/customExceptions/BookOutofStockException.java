package libraryManagementSystem.customExceptions;

public class BookOutofStockException extends RuntimeException{
    public BookOutofStockException(String message) {
        super(message);
    }
}
