/**
 * The Library class contains fields/methods for an object of type Library.
 * Library holds an array of Book objects, and is a Bag-type container.
 * Methods included to search for a Book, add/remove/check-out/return Books
 * to/from the current Library instance, or print out the list of Books.
 @author Michael Choe, Sagnik Mukherjee
 */
@SuppressWarnings({"ManualArrayCopy", "WeakerAccess"})
public class Library
{
    //object fields
    private Book[] books;
    private int numBooks;

    //static constants
    private static final int DEFAULT = 1;

    /**
     * Default constructor.
     */
    public Library()
    {
        books = new Book[1];
        numBooks = 0;
    }

    /**
     * Method to determine if this Library is empty.
     * @return true if this.books is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return (this.numBooks == 0);
    }

    /**
     * Helper method for remove(), finds index of book to be removed.
     * If book is not within Library.books[], return -1.
     * @param book Book to be found within books[], using serial number
     * @return int serial number of book if found, -1 otherwise
     */
    private int find(Book book)
    {
        for (Book curr:books)
            if ((curr != null) && curr.equals(book))
                return Integer.parseInt(curr.getNumber());

        return -1;
    }

    /**
     * Passive helper method, increases books[].length by 4 when needed.
     */
    private void grow()
    {
        Book[] temp = new Book[books.length + 4];
        for (int i = 0; i < numBooks; i++)
            temp[i] = books[i];

        books = temp;
    }

    /**
     * Adds book in available empty slot in Library.books[].
     * @param book Book to be inserted in books[].
     */
    public void add(Book book)
    {
        if (numBooks < books.length && book != null)
        {
            books[numBooks] = book;
            numBooks++;
        }

        //bag is full, need to call grow()
        if (numBooks >= books.length)
            grow();
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
        int removeThis = find(book);
        if (removeThis == -1)
            return false;

        Book[] temp = new Book[books.length - 1];

        //add all Books besides the removed Book object to temp
        for (int i = 0; i < numBooks; i++)
        {
            if ((books[i] != null) &&
                    !(Integer.parseInt(books[i].getNumber()) == removeThis))
                temp[i] = books[i];
        }

        books = temp;
        numBooks--;
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
        return book.tryCheckOut();
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
        return book.tryReturns();
    }

    /**
     * Prints out Library.books[].
     */
    public void print()
    {
        for (Book book:books)
            if (book != null)
                System.out.println(book.toString());
    }

    /**
     * Prints out Library.books[] in ascending order of publication date.
     */
    public void printByDate()
    {
        //using temp array, order of original books[] is preserved
        Book[] temp = new Book[books.length];
        for (int i = 0; i < numBooks; i++)
            temp[i] = books[i];

        sortByDate(temp);

        for (Book book:temp)
            if (book != null)
                System.out.println(book.toString());
    }

    /**
     * Helper method for printByDate().
     * Sorts array in ascending order of publication dates.
     * @param books array containing list of books
     *
     */
    private void sortByDate(Book[] books)
    {
        //insertion sort, shift larger elements to the right as needed
        for (int i = 1; i < numBooks; i++)
        {
            Book key = books[i];
            int j = i - 1;

            while (j >= 0 && (books[j].getDatePublished()
                    .compareTo(key.getDatePublished()) > 0))
            {
                books[j+1] = books[j];
                j = j - 1;
            }
            books[j+1] = key;
        }
    }

    /**
     * Prints out Library.books[] in ascending order of serial number.
     */
    public void printByNumber()
    {
        //using temp array, order of original books[] is preserved
        Book[] temp = new Book[books.length];
        for (int i = 0; i < numBooks; i++)
            temp[i] = books[i];

        sortByNumber(temp);

        for (Book book:temp)
            if (book != null)
                System.out.println(book.toString());
    }

    /**
     * Helper method for printByNumber().
     * Sorts array in ascending order of serial number.
     * @param books array containing list of books
     *
     */
    private void sortByNumber(Book[] books)
    {
        for (int i = 1; i < numBooks; i++)
        {
            Book key = books[i];
            int j = i - 1;

            while (j >= 0
                    && Integer.parseInt(books[j].getNumber())
                    > Integer.parseInt(key.getNumber()))
            {
                books[j+1] = books[j];
                j = j - 1;
            }
            books[j+1] = key;
        }
    }
}
