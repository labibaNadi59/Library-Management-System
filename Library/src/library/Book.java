package library;

import java.util.ArrayList;
import java.time.LocalDate;

public class Book extends Item {
    private String publisherName;

    //constructor for adding new book
    public Book(String title, String category, ArrayList<String> authors, int publishYear, String publisherName) {
        super(title, category, authors, publishYear);
        this.publisherName = publisherName;
    }
//constructor for loading the book info from file
    public Book(String itemId, String title, String category, ArrayList<String> authors, int publishYear, String publisherName) {
        super(itemId, title, category, authors, publishYear);
        this.publisherName = publisherName;
    }

    @Override
    public void checkOut(String memberId) throws CheckedOutException {
        super.checkOut(memberId);
        super.setExpectedReturnDate(LocalDate.now().plusWeeks(2)); // 2 soptaho add korlam
    }

    @Override
    public String toString() {
        return super.toString() + " - " + publisherName;
    }
//getter setter
    public String getPublisherName() { return publisherName; }
    public void setPublisherName(String name) { this.publisherName = name; }
}
