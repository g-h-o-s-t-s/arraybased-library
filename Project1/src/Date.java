import java.util.Calendar;

/**
 * The Date class contains fields/methods for an object of type Date.
 * Three fields make up the month, day, and year of a Date.
 * Methods included to check if a Date is valid (i.e., is not
 * earlier than 1900, and is not in the future).
 * Testbed main used to ensure isValid() is exhaustive as possible.
 @author Michael Choe, Sagnik Mukherjee
 */
public class Date implements Comparable<Date>
{
    //object fields
    private int month;
    private int day;
    private int year;

    //static constants
    private static final int QUADRENNIAL = 4;
    private static final int CENTURY = 100;
    private static final int QUARTERCENTENNIAL = 400;
    private static final int YEARMIN = 1900;
    private static final int ALTMAXDAYS = 30;
    private static final int FEBDAYSLEAP = 29;
    private static final int FEBDAYS = 28;
    private static final int DEFAULTMAXDAYS = 31;

    /*
     * Parameterized constructor.
     * Splits passed string and updates Date fields accordingly.
     * @param date String literal used to update Date field values
     */
    public Date(String date) //taking mm/dd/yyyy and create a Date object
    {
        String[] fields = date.split("/");
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
    }

    /*
     * Default constructor.
     * Uses Calendar class to get current date, and
     * assigns values to fields accordingly.
     */
    public Date()
    {
        Calendar current = Calendar.getInstance();

        //months start from 0 in Calendar class, so increment by 1
        month = current.get(Calendar.MONTH) + 1;
        day = current.get(Calendar.DAY_OF_MONTH);
        year = current.get(Calendar.YEAR);
    }

    /**
     * Determines if Date contents follow the Gregorian Calendar.
     * The year may not be less than 1900, and the day must be
     * <= 28, 29, 30, or 31, depending on the month and
     * whether or not the Date contains a leap year, to be valid.
     * If the date could not be found on a printed calendar for
     * the given year, or is in the future, it is not a valid date.
     * @return true if date is valid, false otherwise
     */
    public boolean isValid()
    {
        //four base-cases: month/day/year out of bounds,
        //or the date is in the future
        if (this.year < YEARMIN)
            return false;

        if (this.month <= 0 || this.month > Calendar.DECEMBER + 1)
            return false;

        if (this.day <= 0 || this.day > DEFAULTMAXDAYS)
            return false;

        if (isInFuture(this.month, this.day, this.year))
            return false;

        //finally, if day is valid, overall Date is valid
        boolean isLeapYear = isLeap();
        return isDayValid(isLeapYear);
    }

    /**
     * Helper method for isValid(), checks if date is in the future.
     * If the date passed has not occurred yet (i.e., is greater
     * than the current date on the Gregorian Calendar),
     * this method returns true.
     * @param month int value for this.month
     * @param day int value for this.day
     * @param year int value for this.year
     * @return true if date is in the future, false otherwise
     */
    private boolean isInFuture(int month, int day, int year)
    {
        Calendar current = Calendar.getInstance();

        int currMonth = current.get(Calendar.MONTH) + 1;
        int currDay = current.get(Calendar.DAY_OF_MONTH);
        int currYear = current.get(Calendar.YEAR);

        return ((year > currYear)
            || (year == currYear && month > currMonth)
            || (year == currYear && month == currMonth
                && day > currDay));
    }

    /**
     * Helper method for isValid(), checks if this.year is leap year.
     * If the year is only divisible by 4, it is a leap year.
     * If it is divisible by 4, 100, and 400, it is a leap year.
     * By assuming it is not a leap year to begin with,
     * we can skip two else-cases.
     * @return true if leap year, false otherwise
     */
    private boolean isLeap()
    {
        boolean result = false;
        if (year % QUADRENNIAL == 0)
        {
            if (year % CENTURY == 0)
            {
                if (year % QUARTERCENTENNIAL == 0)
                    result = true;
                //else false
            }
            else
                result = true;
        }
        //else false
        return result;
    }

    /**
     * Helper method for isValid(), checks if this.day is valid.
     * Most months on the Gregorian calendar have either 30 or 31 days.
     * February is the exception, having 28 days, or 29 in a leap year.
     * Thus, we use a switch-case structure to validate if this.day
     * fits within the constraints, which depend on the month.
     * @param isLeapYear boolean from isLeap() method call in isValid()
     * @return true if day valid, false otherwise
     */
    private boolean isDayValid(boolean isLeapYear)
    {
        switch (this.month)
        {
            case (Calendar.APRIL + 1):
            case (Calendar.JUNE + 1):
            case (Calendar.SEPTEMBER + 1):
            case (Calendar.NOVEMBER + 1):
                if (this.day > ALTMAXDAYS) //months with 30 days
                    return false;
                break;

            case (Calendar.FEBRUARY + 1):
                if ((isLeapYear && this.day > FEBDAYSLEAP) //>29
                        || (!isLeapYear && this.day > FEBDAYS)) //>28
                    return false;
                break;

            default: //months with 31 days
                if (this.day > DEFAULTMAXDAYS)
                    return false;
                break;
        }
        return true;
    }

    /**
     * Compare passed Date (that) with invoking Date object (this).
     * @param that Date to be compared to by invoking object
     * @return -1 if this < that, 1 if this > that, 0 if objects are equal
     */
    public int compareTo(Date that)
    {
        if ((this.year < that.year)
                || (this.year == that.year && this.month < that.month)
                || (this.year == that.year && this.month == that.month
                && this.day < that.day))
            return -1;
        else if ((this.year > that.year)
                || (/*this.year == that.year && */this.month > that.month)
                || (/*this.year == that.year && this.month == that.month &&*/
                this.day > that.day))
            return 1;

        return 0;
    }

    /**
     * Returns Date contents as string literal.
     * @return String literal of Book field values
     */
    @Override
    public String toString()
    {
        return String.format("%d/%d/%d", this.month,
                this.day, this.year);
    }

    /**
     * Testbed main method for Date object class.
     * Instantiates Dates and checks their validity.
     * @param args The command line arguments.
     */
    public static void main(String args[])
    {
        System.out.println("TESTBED MAIN - DATE() CLASS" +
                "\n---------------------------");

        //Test 1 - Is Today's Date Valid? (expected true)
        Calendar rightNow = Calendar.getInstance();
        String str1 = String.format("%d/%d/%d",
                rightNow.get(Calendar.MONTH) + 1,
                rightNow.get(Calendar.DAY_OF_MONTH),
                rightNow.get(Calendar.YEAR));
        Date date1 = new Date(str1);
        System.out.println("Now testing \"" + date1.toString() + "\".");
        boolean test1 = date1.isValid();
        System.out.println("Test 1 returned " + test1 + ".");

        //Test 2 - Is 1/1/1900 Valid? (expected true)
        String str2 = "1/1/1900";
        Date date2 = new Date(str2);
        System.out.println("Now testing \"" + date2.toString() + "\".");
        boolean test2 = date2.isValid();
        System.out.println("Test 2 returned " + test2 + ".");

        //Test 3 - Is 1/31/1979 Valid? (expected true)
        String str3 = "1/31/1979";
        Date date3 = new Date(str3);
        System.out.println("Now testing \"" + date3.toString() + "\".");
        boolean test3 = date3.isValid();
        System.out.println("Test 3 returned " + test3 + ".");

        //Test 4 - Is 02/28/1999 Valid? (expected true)
        String str4 = "02/28/1999";
        Date date4 = new Date(str4);
        System.out.println("Now testing \"" + date4.toString() + "\".");
        boolean test4 = date4.isValid();
        System.out.println("Test 4 returned " + test4 + ".");

        //Test 5 - Is 02/29/1999 Valid? (expected false)
        String str5 = "02/29/1999";
        Date date5 = new Date(str5);
        System.out.println("Now testing \"" + date5.toString() + "\".");
        boolean test5 = date5.isValid();
        System.out.println("Test 5 returned " + test5 + ".");

        //Test 6 - Is 02/29/2000 Valid? (expected true)
        String str6 = "02/29/2000";
        Date date6 = new Date(str6);
        System.out.println("Now testing \"" + date6.toString() + "\".");
        boolean test6 = date6.isValid();
        System.out.println("Test 6 returned " + test6 + ".");

        //Test 7 - Is 02/30/2000 Valid? (expected false)
        String str7 = "02/30/2000";
        Date date7 = new Date(str7);
        System.out.println("Now testing \"" + date7.toString() + "\".");
        boolean test7 = date7.isValid();
        System.out.println("Test 7 returned " + test7 + ".");

        //Test 8 - Is 09/31/2012 Valid? (expected false)
        String str8 = "09/31/2012";
        Date date8 = new Date(str8);
        System.out.println("Now testing \"" + date8.toString() + "\".");
        boolean test8 = date8.isValid();
        System.out.println("Test 8 returned " + test8 + ".");

        //Test 9 - Is Tomorrow's Date Valid? (expected false)
        String str9 = String.format("%d/%d/%d",
                rightNow.get(Calendar.MONTH) + 1,
                rightNow.get(Calendar.DAY_OF_MONTH) + 1,
                rightNow.get(Calendar.YEAR));
        Date date9 = new Date(str9);
        System.out.println("Now testing \"" + date9.toString() + "\".");
        boolean test9 = date9.isValid();
        System.out.println("Test 9 returned " + test9 + ".");

        //Test 10 - Is Next Year's Date Valid? (expected false)
        String str10 = String.format("%d/%d/%d",
                rightNow.get(Calendar.MONTH) + 1,
                rightNow.get(Calendar.DAY_OF_MONTH),
                rightNow.get(Calendar.YEAR) + 1);
        Date date10 = new Date(str10);
        System.out.println("Now testing \"" + date10.toString() + "\".");
        boolean test10 = date10.isValid();
        System.out.println("Test 10 returned " + test10 + ".");
    }
}