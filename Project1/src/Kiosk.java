import java.util.Scanner;

/**
 * The Kiosk class acts as the Client class for this project.
 * Using built-in Scanner class methods, the Kiosk will interact
 * with a user via the console, and handle various cases of
 * valid/invalid user commands.
 @author Michael Choe, Sagnik Mukherjee
 */
@SuppressWarnings("WeakerAccess")
public class Kiosk
{
    /**
     * Driver method to run Kiosk commands.
     */
    public void run()
    {
        printout(Consts.STARTUP);
        Library library = new Library();
        Scanner scn = new Scanner(System.in);
        String input;
        String[] inputs;
        int bookCounter = 0;
        boolean loop = true;

        while (loop && scn.hasNextLine())
        {
            input = scn.nextLine();
            if (input.equals(Consts.QUIT))
            {
                loop = false;
                continue;
            }

            inputs = input.split(Consts.DELIMITER);
            String command = inputs[Consts.SPLITONE];
            switch (command)
            {
                case Consts.ADD:
                    addBook(inputs, bookCounter, library);
                    bookCounter++;
                    break;

                case Consts.REMOVE:
                    removeBook(inputs, library);
                    break;

                case Consts.CHECKOUT:
                    checkOutBook(inputs, library);
                    break;

                case Consts.RETURN:
                    returnBook(inputs, library);
                    break;

                case Consts.PRINTALL:
                    if (library.isEmpty())
                        printout(Consts.ISEMPTY);
                    else
                        library.print();
                    break;

                case Consts.PRINTDATE:
                    if (library.isEmpty())
                        printout(Consts.ISEMPTY);
                    else
                        library.printByDate();
                    break;

                case Consts.PRINTNUM:
                    if (library.isEmpty())
                        printout(Consts.ISEMPTY);
                    else
                        library.printByNumber();
                    break;

                default: //invalid input
                    printout(Consts.INVALID);
                    break;
            }
        }
        printout(Consts.SHUTDOWN);
    }

    /**
     * Helper method to execute "Add" client command.
     * @param inputs String[] reference pass of return value of split()
     * @param bookCounter int, tracks how many Books have been instantiated
     * @param library Library, reference pass of library bag container
     */
    private void addBook(String[] inputs, int bookCounter, Library library)
    {
        if (inputs.length == Consts.THREEINPUTS)
        {
            String title = inputs[Consts.SPLITTWO];
            String date = inputs[Consts.SPLITTHREE];

            Date check = new Date(date);
            if (check.isValid())
            {
                Book addThis = new Book(bookCounter
                        + Consts.SERIAL, title, date);
                library.add(addThis);
                printout(title + " added to the Library.");
            }
            else
                printout("Invalid Date!");
        }
        else
            printout(Consts.INVALID);
    }

    /**
     * Helper method to execute "Remove" client command.
     * @param inputs String[] reference pass of return value of split()
     * @param library Library, reference pass of library bag container
     */
    private void removeBook(String[] inputs, Library library)
    {
        if (inputs.length == Consts.TWOINPUTS)
        {
            try {
                int number = Integer.parseInt(inputs[Consts.SPLITTWO]);
                Book key = new Book(number);

                boolean removed = library.remove(key);

                if (removed)
                    printout("Book# " + number + " removed.");
                else
                    printout("Unable to remove, the library " +
                            "does not have this book.");
            } catch (NumberFormatException ex)
            {
                printout(Consts.INVALID);
            }
        }
        else
            printout(Consts.INVALID);
    }

    /**
     * Helper method to execute "CheckOut" client command.
     * @param inputs String[] reference pass of return value of split()
     * @param library Library, reference pass of library bag container
     */
    private void checkOutBook(String[] inputs, Library library)
    {
        if (inputs.length == Consts.TWOINPUTS)
        {
            try
            {
                int number = Integer.parseInt(inputs[Consts.SPLITTWO]);
                Book key = new Book(number);

                boolean checkedOut = library.checkOut(key);

                if (checkedOut)
                    printout("You've checked out Book#" + number
                            + ". Enjoy!");
                else
                    printout("Book#" + number + " is not available.");
            } catch (NumberFormatException ex)
            {
                printout(Consts.INVALID);
            }
        }
        else
            printout(Consts.INVALID);
    }

    /**
     * Helper method to execute "Return" client command.
     * @param inputs String[] reference pass of return value of split()
     * @param library Library, reference pass of library bag container
     */
    private void returnBook(String[] inputs, Library library)
    {
        if (inputs.length == Consts.TWOINPUTS)
        {
            try
            {
                int number = Integer.parseInt(inputs[Consts.SPLITTWO]);
                Book key = new Book(number);

                boolean returned = library.returns(key);

                if (returned)
                    printout("Book#" + number
                            + " return has completed. Thanks!");
                else
                    printout("Unable to return Book#" + number + ".");
            } catch (NumberFormatException ex)
            {
                printout(Consts.INVALID);
            }
        }
        else
            printout(Consts.INVALID);
    }

    /**
     * Helper method to print given string.
     * Reduces the repetitions of "System.out.println" in Kiosk.
     * @param str String literal to be printed
     */
    private void printout(String str)
    {
        System.out.println(str);
    }
}
