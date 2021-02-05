/**
 * The Library class contains fields/methods for an object of type Library.
 * Library holds an array of Book objects, and is a Bag-type container.
 * Methods included to search for a Book, add/remove/check-out/return Books
 * to/from the current Library instance, or print out the list of Books.
 @author Michael Choe, Sagnik Mukherjee
 */
public class Library
{
    //TO-DO: implement all methods, add helper methods as needed

    //object fields
    private Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    //static constants
    private static final int MAXSIZE = 10;

    /*
     * Default constructor.
     */
    public Library()
    {
        books = new Book[MAXSIZE];
        numBooks = books.length;
    }

    /**
     * Helper method for remove(), finds index of book to be removed.
     * If book is not within Library.books[], return -1.
     * @param book Book to be found within books[], using serial number
     * @return int serial number of book if found, -1 otherwise
     */
    private int find(Book book)
    {
        return -1;
    }

    /**
     * Passive helper method, increases books[].length by 4 when needed.
     */
    private void grow()
    {

    }

    /**
     * Adds book to the end of Library.books[].
     * @param book Book to be appended to books[].
     */
    public void add(Book book)
    {

    }

    /**
     * Removes book param from Library.books[], maintains array order.
     * Uses find() as helper method. If book is not within array,
     * return false (i.e., remove() failed).
     * @param book Book to be removed from books[]
     * @return true if book was removed, false otherwise
     */
    public boolean remove(Book book)
    {
        int found = find(book);
        if (found == -1)
            return false;

        //remove book
        return true;
    }

    /**
     * Checks out a book from Library, by flipping its checkedOut field.
     * @param book Book to be checked out from Library
     * @return true if book was checked out, false otherwise
     */
    public boolean checkOut(Book book)
    {
        int found = find(book);
        if (found == -1)
            return false;

        //attempt to check out book
        if (!book.tryCheckOut())
            return false;

        return true;
    }

    /**
     * Returns book to Library, by flipping its checkedOut field.
     * @param book Book to be returned to Library
     * @return true if book was returned, false otherwise
     */
    public boolean returns(Book book)
    {
        int found = find(book);
        if (found == -1)
            return false;

        //attempt to return book
        if (!book.tryReturns())
            return false;

        return true;

    }

    /**
     * Prints out Library.books[].
     */
    public void print()
    {
        for (Book i:books)
        {
            System.out.println(book[i].toString());
        }
    }

    /**
     * Prints out Library.books[] in ascending order of publication date.
     */
    public void printByDate()
    {

    }

    /**
     * Prints out Library.books[] in ascending order of serial number.
     */
    public void printByNumber()
    {

    }
}
