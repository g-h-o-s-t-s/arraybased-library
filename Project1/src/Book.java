/**
 * The Book class contains fields/methods for an object of type Book.
 * Each Book has an automatically-generated 5-digit serial number.
 * Methods included to compare Books, as well as return
 * a String literal describing their data field contents.
 @author Michael Choe, Sagnik Mukherjee
 */
@SuppressWarnings({"WeakerAccess", "unused", "FieldMayBeFinal"})
public class Book implements Comparable<Book>
{
    //object fields
    private String number; //5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    /**
     * Parameterized constructor.
     * @param num int, will hold serial number
     * @param title String, title of Book object
     * @param date String, date used for parameterized Date() constructor
     */
    public Book(int num, String title, String date)
    {
        number = "" + num;
        name = title;
        checkedOut = false;
        datePublished = new Date(date);
    }

    /**
     * Parameterized constructor, takes only the serial number.
     * @param num int, will hold serial number
     */
    public Book(int num)
    {
        number = "" + num;
        name = "";
        checkedOut = false;
        datePublished = new Date();
    }

    /**
     * Default constructor.
     */
    public Book()
    {
        number = "";
        name = "";
        checkedOut = false;
        datePublished = new Date();
    }

    /**
     * Getter, returns number field for this Book.
     * @return int containing this.number value
     */
    public int getNumber()
    {
        return Integer.parseInt(number);
    }

    /**
     * Getter, returns datePublished field for this Book.
     * @return String literal containing this.book.datePublished value
     */
    public Date getDatePublished()
    {
        return datePublished;
    }

    /**
     * Attempts to check out this Book object.
     * If the book is already checked out, tryCheckOut() fails and we cannot
     * check out the book. If it is available, the book is now checked out,
     * and tryCheckOut() succeeds.
     * @return false if this.checkedOut is initially true, true otherwise
     */
    public boolean tryCheckOut()
    {
        if (this.checkedOut)
            return false;
        else
            this.checkedOut = true;
        return true;
    }

    /**
     * Attempts to return this Book object.
     * If the book is already available, tryReturns() fails and we cannot
     * return the book. If it is checked out, the book is now returned,
     * and tryReturns() succeeds.
     * @return false if this.checkedOut is initially false, true otherwise
     */
    public boolean tryReturns()
    {
        if (!this.checkedOut)
            return false;
        else
            this.checkedOut = false;
        return true;
    }

    /**
     * Determines if two Books have the same serial number.
     * @param obj Object to be compared to by invoking object
     * @return true if serial numbers are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        //equals method should first confirm invoking object and
        //parameter are of the same class
        if (getClass() != obj.getClass())
            return false;

        Book that = (Book) obj;
        return this.number.equals(that.number);
    }

    /**
     * Compare two Book objects, first by number, then name, then date
     * @param that Book to be compared to by invoking object
     * @return -1 if this is less, 1 if this is greater, 0 if equal
     */
    public int compareTo(Book that)
    {
        int thisNumber = this.getNumber();
        int thatNumber = that.getNumber();

        int numComparison = Integer.compare(thisNumber, thatNumber);

        //same serial number, compare book titles
        if (numComparison == 0)
        {
            int nameComparison = this.name.compareTo(that.name);
            //same title, compare publication dates
            if (nameComparison == 0)
                return this.datePublished.compareTo(that.datePublished);
        }

        //different serial numbers
        return numComparison;
    }

    /**
     * Returns Book contents as string literal. There are two possible
     * return values, depending on the value of checkedOut.
     * @return String literal of Book field values
     */
    @Override
    public String toString()
    {
        if (checkedOut)
            return "Book#" + number + "::" + name + "::" + datePublished
                    + "::is checked out.";
        else
            return "Book#" + number + "::" + name + "::" + datePublished
                + "::is available.";
    }
}
