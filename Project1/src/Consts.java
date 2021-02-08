/**
 * The Consts class houses all immutable constant values.
 * These values are used throughout the project to avoid the ambiguity
 * brought about by the use of "magic numbers".
 @author Michael Choe, Sagnik Mukherjee
 */
@SuppressWarnings("WeakerAccess")
public class Consts
{
    /* Date() class constants */
    //Used to determine if given date is leap year.
    public static final int QUADRENNIAL = 4;
    public static final int CENTURY = 100;
    public static final int QUARTERCENTENNIAL = 400;
    //Used to determine max amount of days given month should have.
    public static final int ALTMAXDAYS = 30;
    public static final int FEBDAYSLEAP = 29;
    public static final int FEBDAYS = 28;
    public static final int DEFAULTMAXDAYS = 31;
    //Used to represent earliest year possible for a given Date object.
    public static final int YEARMIN = 1900;

    /* Library() class constants */
    //Used to determine size of Library.books[] in constructor
    public static final int DEFAULT = 1;

    /* Kiosk() class constants */
    //Default serial number, added to the nth-instance of Book().
    public static final int SERIAL = 10001;
    //Used to validate given Kiosk input command.
    //If the input starts with "A" for example, the string would
    //be expected to have two commas, so split() returns three substrings.
    //"R", "O", and "I" would have two substrings for their input lines.
    public static final int THREEINPUTS = 3;
    public static final int TWOINPUTS = 2;
    //Output messages/String literal constant values used in Kiosk.
    public static final String STARTUP = "Library Kiosk running.";
    public static final String QUIT = "Q";
    public static final String DELIMITER = ",";
    public static final String ADD = "A";
    public static final String REMOVE = "R";
    public static final String CHECKOUT = "O";
    public static final String RETURN = "I";
    public static final String PRINTALL = "PA";
    public static final String ISEMPTY = "Library catalog is empty!";
    public static final String PRINTDATE = "PD";
    public static final String PRINTNUM = "PN";
    public static final String INVALID = "Invalid command!";
    public static final String SHUTDOWN = "Kiosk session ended.";


    /**
     * Constructor prevents other classes from instantiating objects of
     * type Consts when calling this class.
     */
    private Consts()
    {
        throw new AssertionError();
    }
}
