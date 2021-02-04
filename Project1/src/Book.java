/**
* The Book class encapsulates fields/methods for an object of type Book.
* Each book object has an automatically-generated, 5-digit serial number.
* Methods are provided to compares Books, as well as return
* a String literal describing their data field contents.
@author Michael Choe, Sagnik Mukherjee
*/

public class Book
{
    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    //TO-DO: add methods where necessary

    /**
    * Determines if two Books have the same serial number.
    * @param obj to be compared to by invoking object
    * @return true if serial numbers are equal, false otherwise
    */
    @Override
    public boolean equals(Object obj)
    {
        return this.number == ((Book) obj).number;
    }

    /**
    * @return String literal of Book field values
    */
    @Override
    public String toString()
    {
        return "Book#" + number + "::" + name + "::" + datePublished + "::is available.";
    }
}
