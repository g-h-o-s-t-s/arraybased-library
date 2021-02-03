public class Date
{
    private int year;
    private int month;
    private int day;

    public Date(String date) //taking mm/dd/yyyy and create a Date object
    {
        this.month = 10*date.charAt(0) + date.charAt(1);
        this.day = 10*date.charAt(3) + date.charAt(4);
        this.year = 1000*date.charAt(6) + 100*date.charAt(7) + 10*date.charAt(8) + date.charAt(9);
    }
    public Date() //return today’s date
    {

    }

    //TO-DO: add methods where necessary

    //boolean return type?
    public isValid()
    {
      /*  if(year < 1900 || year > 2021){
               return false;
      */
        
      //check for leap year
      /*
        if(
      */
    }
}
