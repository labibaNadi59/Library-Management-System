package library;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class Item implements Serializable {
    private String itemId;
    private String title;
    private String category;
    private ArrayList<String> authors;
    private int publishYear;
    private boolean isCheckedOut;
    private String checkedOutBy;
    private LocalDate expectedReturnDate;

    //constructor
    public Item(String title, String category, ArrayList<String> authors, int publishYear) {
        this.itemId = String.format("%04d", new Random().nextInt(10000));
        this.title = title;
        this.category = category;
        this.authors = authors;
        this.publishYear = publishYear;
        this.isCheckedOut = false;
        this.checkedOutBy = null;
        this.expectedReturnDate = null;
    }

    public Item(String itemId, String title, String category, ArrayList<String> authors, int publishYear) {
        this.itemId = itemId;
        this.title = title;
        this.category = category;
        this.authors = authors;
        this.publishYear = publishYear;
        this.isCheckedOut = false;
        this.checkedOutBy = null;
        this.expectedReturnDate = null;
    }

    //methods
    public void checkOut(String memberId) throws CheckedOutException {
        if (isCheckedOut)
            throw new CheckedOutException(this.getClass().getSimpleName(), title, itemId);
        this.isCheckedOut =true;
        this.checkedOutBy =memberId;
    }

    public void checkIn() {
        if (!isCheckedOut)
            return;
        this.isCheckedOut =false;
        this.checkedOutBy =null;
        this.expectedReturnDate =null;
    }

    public boolean isAnAuthor(String author) {
        return authors.stream().anyMatch(a -> a.toLowerCase().contains(author.toLowerCase()));
    }
//getter setter
    public String getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public ArrayList<String> getAuthors() { return authors; }
    public int getPublishYear() { return publishYear; }
    public boolean getIsCheckedOut() { return isCheckedOut; }
    public String getCheckedOutBy() { return checkedOutBy; }
    public LocalDate getExpectedReturnDate() { return expectedReturnDate; }

    public void setCheckOutStatus(boolean checkedOut) { this.isCheckedOut = checkedOut; }
    public void setCheckedOutBy(String checkedOutBy) { this.checkedOutBy = checkedOutBy; }
    public void setExpectedReturnDate(LocalDate returnDate) { this.expectedReturnDate = returnDate; }

    @Override
    public String toString() {
        String returnDateStr = (expectedReturnDate == null) ? "N/A" : expectedReturnDate.toString();
        return itemId + " - " + title + " - " + String.join(",", authors) + " - " + publishYear
                + " - Checked Out: " + (isCheckedOut ? "Yes" : "No")
                + " - Expected Return Date: " + returnDateStr;
    }


}
