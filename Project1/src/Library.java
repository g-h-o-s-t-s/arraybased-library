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

    /**
     * Default constructor.
     */
    public Library()
    {
        books = new Book[Consts.DEFAULT];
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
     * @return index of book if found, -1 otherwise
     */
    private int find(Book book)
    {
        for (int i = 0; i < books.length; i++)
            if ((books[i] != null) && books[i].equals(book))
                return i;

        return -1;
    }

    /**
     * Passive helper method, increases books[].length by 4 when needed.
     */
    private void grow()
    {
        Book[] temp = new Book[books.length + Consts.GROW];
        for (int i = 0; i < books.length; i++)
            temp[i] = books[i];

        books = temp;
    }

    /**
     * Adds book in available empty slot in Library.books[].
     * @param book Book to be inserted in books[].
     */
    public void add(Book book)
    {
        //bag is full, need to call grow()
        if (numBooks >= books.length)
            grow();

        for (int i = 0; i < books.length; i++)
            if (books[i] == null)
            {
                books[i] = book;
                break;
            }

        numBooks++;
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

        books[removeThis] = null;

        //shift successive elements to the right
        for (int i = removeThis + 1; i < books.length; i++)
        {
            int last = i - 1;
            try
            {
                books[last] = books[i];
            } catch (ArrayIndexOutOfBoundsException ex)
            {
                break;
            }
        }
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
        return books[found].tryCheckOut();
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
        return books[found].tryReturns();
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
        sortByDate(books);
        print();
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
        for (int i = 1; i < books.length; i++)
        {
            int j = i - 1;
            if (books[i] != null && books[j] != null)
            {
                Book key = books[i];

                while (j >= 0 && books[j] != null
                        && (books[j].getDatePublished()
                        .compareTo(key.getDatePublished()) > 0))
                {
                    books[j + 1] = books[j];
                    j = j - 1;
                }
                books[j + 1] = key;
            }
        }
    }

    /**
     * Prints out Library.books[] in ascending order of serial number.
     */
    public void printByNumber()
    {
        sortByNumber(books);
        print();
    }

    /**
     * Helper method for printByNumber().
     * Sorts array in ascending order of serial number.
     * @param books array containing list of books
     *
     */
    private void sortByNumber(Book[] books)
    {
        for (int i = 1; i < books.length; i++)
        {
            int j = i - 1;
            if (books[i] != null && books[j] != null)
            {
                Book key = books[i];

                while (j >= 0 && books[j] != null
                        && books[j].compareTo(key) > 0)
                {
                    books[j + 1] = books[j];
                    j = j - 1;
                }
                books[j + 1] = key;
            }
        }
    }
}
