package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Library implements Serializable {
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<String> members = new ArrayList<>();
    private static final String ITEMS_FILE = "items.txt";
    private static final String MEMBERS_FILE = "members.txt";

    public Library() {
        loadData();
    }

    private void loadData() {
        loadItems();
        loadMembers();
    }

    private void loadItems() {
        File file = new File(ITEMS_FILE);
        if (!file.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length < 9) continue;

                String itemId = parts[0];
                String type = parts[1];
                String title = parts[2];
                String category = parts[3];
                ArrayList<String> authors = new ArrayList<>(Arrays.asList(parts[4].split(",")));
                int publishYear = Integer.parseInt(parts[5]);
                boolean isCheckedOut = Boolean.parseBoolean(parts[6]);
                String checkedOutBy = parts[7].equals("null") ? null : parts[7];
                LocalDate expectedReturnDate = parts[8].equals("null") ? null : LocalDate.parse(parts[8]);

                Item item = null;
                if (type.equalsIgnoreCase("Book") && parts.length >= 10) {
                    String publisherName = parts[9];
                    item = new Book(itemId, title, category, authors, publishYear, publisherName);
                }
                else if (type.equalsIgnoreCase("Publication") && parts.length >= 10) {
                    String journalOrConference = parts[9];
                    item = new Publication(itemId, title, category, authors, publishYear, journalOrConference);
                }

                if (item != null) {
                    item.setCheckOutStatus(isCheckedOut);
                    item.setCheckedOutBy(checkedOutBy);
                    item.setExpectedReturnDate(expectedReturnDate);
                    items.add(item);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error loading items: " + e.getMessage());
        }
    }

    private void loadMembers() {
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                members.add(scanner.nextLine().trim());//whitespace trim kore dey
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error loading members: " + e.getMessage());
        }
    }

    public void saveData() {
        saveItems();
        saveMembers();
    }

    private void saveItems() {
        try (PrintStream ps = new PrintStream(ITEMS_FILE)) {
            for (Item item : items) {
                StringBuilder sb = new StringBuilder();
                sb.append(item.getItemId()).append("|")
                        .append(item instanceof Book ? "Book" : (item instanceof Publication ? "Publication" : "Unknown")).append("|")
                        .append(item.getTitle()).append("|")
                        .append(item.getCategory()).append("|")
                        .append(String.join(",", item.getAuthors())).append("|")
                        .append(item.getPublishYear()).append("|")
                        .append(item.getIsCheckedOut()).append("|")
                        .append(item.getCheckedOutBy() == null ? "null" : item.getCheckedOutBy()).append("|")
                        .append(item.getExpectedReturnDate() == null ? "null" : item.getExpectedReturnDate().toString()).append("|");

                if (item instanceof Book) {
                    sb.append(((Book) item).getPublisherName());
                }
                else if (item instanceof Publication) {
                    sb.append(((Publication) item).getJournalOrConference());
                }
                ps.println(sb.toString());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving items: " + e.getMessage());
        }
    }

    private void saveMembers() {
        try (PrintStream ps = new PrintStream(MEMBERS_FILE)) {
            for (String memberId : members) {
                ps.println(memberId);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving members: " + e.getMessage());
        }
    }

    //object of type item means, book or publication(Subclass polymorphism)
    private void addItem(Item item) {
        items.add(item);
    }

    public void addBook(String title, String category, ArrayList<String> authors, int year, String publisher) {
        Book book = new Book(title, category, authors, year, publisher);
        addItem(book);
    }

    public void addPublication(String title, String category, ArrayList<String> authors, int year, String journalOrConference) {
        Publication pub = new Publication(title, category, authors, year, journalOrConference);
        addItem(pub);
    }

    public Item findItem(String itemId) throws InvalidItemException {
        for (Item i : items) {
            if (i.getItemId().equals(itemId))
                return i;
        }
        throw new InvalidItemException(itemId);
    }

    public ArrayList<Item> findItems(String title, String author) {
        ArrayList<Item> found = new ArrayList<>();
        for (Item i : items) {
            if (i.getTitle().toLowerCase().contains(title.toLowerCase()) && i.isAnAuthor(author)) {
                found.add(i);
            }
        }
        return found.isEmpty() ? null : found;
    }

    public ArrayList<Item> findItemsByYear(int year) {
        ArrayList<Item> found = new ArrayList<>();
        for (Item i : items) {
            if (i.getPublishYear() == year) {
                found.add(i);
            }
        }
        return found.isEmpty() ? null : found;
    }

    public ArrayList<Item> findItems(String type) {
        ArrayList<Item> found = new ArrayList<>();
        for (Item i : items) {
            if (i.getClass().getSimpleName().equalsIgnoreCase(type)) {
                found.add(i);
            }
        }
        return found.isEmpty() ? null : found;
    }

    public void checkOut(String itemId, String memberId) throws CheckedOutException, InvalidItemException, InvalidMemberException {
        Item item = findItem(itemId);
        if (!findMember(memberId)) {
            throw new InvalidMemberException(memberId);
        }
        item.checkOut(memberId);
    }

    public void checkIn(String itemId) throws InvalidItemException {
        findItem(itemId).checkIn();
    }

    public void addMember(String id) {
        if (!members.contains(id)) {
            members.add(id);
        }
    }

    public boolean findMember(String id) throws InvalidMemberException {
        return members.contains(id);
    }

    public ArrayList<Item> getAllItems() {
        return items;
    }
}
