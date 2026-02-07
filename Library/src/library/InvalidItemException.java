package library;

public class InvalidItemException extends Exception {
    public InvalidItemException(String id) {
        super(String.format("Item %s is not a valid item", id));
    }
}
