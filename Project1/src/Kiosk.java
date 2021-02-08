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
        print("Library Kiosk running.");
        Library library = new Library();
        Scanner scn = new Scanner(System.in);
        String input;
        String[] inputs;
        int bookCounter = 0;
        boolean loop = true;

        while (loop && scn.hasNextLine())
        {
            input = scn.nextLine();
            if (input.equals("Q"))
            {
                loop = false;
                continue;
            }

            inputs = input.split(",");
            String command = inputs[0];
            //TO-DO: break cases off into helper methods
            //TO-DO: commands are not thorough and throw various exceptions,
            // so far only adding works as intended
            switch (command)
            {
                case "A":
                    addBook(inputs, bookCounter, library);
                    bookCounter++;
                    break;

                case "R":
                    removeBook(inputs, library);
                    break;

                case "O":
                    checkOutBook(inputs, library);
                    break;

                case "I":
                    returnBook(inputs, library);
                    break;

                case "PA":
                    if (library.isEmpty())
                        print("Library catalog is empty!");
                    else
                        library.print();
                    break;

                case "PD":
                    if (library.isEmpty())
                        print("Library catalog is empty!");
                    else
                        library.printByDate();
                    break;

                case "PN":
                    if (library.isEmpty())
                        print("Library catalog is empty!");
                    else
                        library.printByNumber();
                    break;

                default: //invalid input
                    print("Invalid command!");
                    break;
            }
        }

        print("Kiosk session ended.");
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
            String title = inputs[1];
            String date = inputs[2];

            Date check = new Date(date);
            if (check.isValid())
            {
                Book addThis = new Book(bookCounter
                        + Consts.SERIAL, title, date);
                library.add(addThis);
                print(title + " added to the Library.");
            }
            else
                print("Invalid Date!");
        }
        else
            print("Invalid command!");
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
            int number = Integer.parseInt(inputs[1]);
            Book key = new Book(number);

            boolean removed = library.remove(key);

            if (removed)
                print("Book# " + number + " removed.");
            else
                print("Unable to remove, the library " +
                        "does not have this book.");
        }
        else
            print("Invalid command!");
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
            int number = Integer.parseInt(inputs[1]);
            Book key = new Book(number);

            boolean checkedOut = library.checkOut(key);

            if (checkedOut)
                print("You've checked out Book#" + number
                        + ". Enjoy!");
            else
                print("Book#" + number + " is not available.");
        }
        else
            print("Invalid command!");
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
            int number = Integer.parseInt(inputs[1]);
            Book key = new Book(number);

            boolean returned = library.returns(key);

            if (returned)
                print("Book#" + number
                        + " return has completed. Thanks!");
            else
                print("Unable to return Book#" + number + ".");
        }
        else
            print("Invalid command!");
    }

    /**
     * Helper method to print given string.
     * Reduces the repetitions of "System.out.println" in Kiosk.
     * @param str String literal to be printed
     */
    private void print(String str)
    {
        System.out.println(str);
    }
}
