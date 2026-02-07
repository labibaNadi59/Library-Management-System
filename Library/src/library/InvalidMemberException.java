package library;

public class InvalidMemberException extends Exception {
    public InvalidMemberException() {
        super("Not a valid member");
    }

    public InvalidMemberException(String id) {
        super(String.format("%s is not a member", id));
    }
}
