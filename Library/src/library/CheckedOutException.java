package library;

public class CheckedOutException extends Exception {
    public CheckedOutException(String itemType, String title, String itemId) {
        super(String.format("%s \"%s\" (%s) is already checked out.", itemType, title, itemId));
    }

    public CheckedOutException(String msg) {
        super(msg);
    }
}
