/**
 * The Book class contains fields/methods for an object of type Book.
 * Each Book has an automatically-generated 5-digit serial number.
 * Methods included to compare Books, as well as return
 * a String literal describing their data field contents.
 @author Michael Choe, Sagnik Mukherjee
 */
public class Book
{
    private String number; //5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    /*
     * Default constructor.
     */
    public Book()
    {
        number = "10001";
        name = "";
        checkedOut = false;
        datePublished = new Date();
    }

    /**
     * Updates number field for this Book.
     * @param newNum String, will be invoking object's new serial number
     */
    public void setBookNum(String newNum)
    {
        this.number = newNum;
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
     * Returns Book contents as string literal. There are two possible
     * return values, depending on the value of checkedOut.
     * @return String literal of Book field values
     */
    @Override
    public String toString()
    {
        if (checkedOut)
            return "Book#" + number + "::" + name + "::" + datePublished
                    + "::is not available.";
        else
            return "Book#" + number + "::" + name + "::" + datePublished
                + "::is available.";
    }
}