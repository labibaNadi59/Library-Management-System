package library;

import java.util.ArrayList;
import java.time.LocalDate;

public class Publication extends Item {
    private String journalOrConference;
//constructor
    public Publication(String title, String category, ArrayList<String> authors, int publishYear, String journalOrConference) {
        super(title, category, authors, publishYear);
        this.journalOrConference = journalOrConference;
    }
//constructor for loading the publication info
    public Publication(String itemId, String title, String category, ArrayList<String> authors, int publishYear, String journalOrConference) {
        super(itemId, title, category, authors, publishYear);
        this.journalOrConference = journalOrConference;
    }

    @Override
    public void checkOut(String memberId) throws CheckedOutException {
        super.checkOut(memberId);
        super.setExpectedReturnDate(LocalDate.now().plusWeeks(1)); // 1 soptaho
    }

    @Override
    public String toString() {
        return super.toString() + " - " + journalOrConference;
    }

    //getter setter
    public String getJournalOrConference() {
        return journalOrConference;
    }

    public void setJournalOrConference(String journalOrConference) {
        this.journalOrConference = journalOrConference;
    }
}
