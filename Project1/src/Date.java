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
    public void setYear(int year){
        this.year = year;
    }
    
    public void setMonth(int month){
        this.month = month;
    }
    
    public void setDay(int day){
        this.day = day;
    }

    public void getYear(){
        return this.year;
    }
    
    public void getMonth(){
        return this.month;
    }
    
    public void getDay(){
        return this.day;
    }
    
    //boolean return type?
    public isValid()
    {
      /*  if(year < 1900 || year > 2021){
               return false;
      */
      
      //check for leap year
      /*
        boolean isLeapYear;
        if(year%4 == 0){
            if(year%100 == 0){
                if(year%400 == 0){
                    isLeapYear = true;
                }else{
                    isLeapYear = false;
                }
            }else{
                isLeapYear = true;
            }
        }else{
            isLeapYear = false;
        }
           
        //checks if the day is valid
        switch(month){
            case 4:
            case 6:
            case 9:
            case 11:
                if(day > 30){
                    return false;
                }
                break;
            case 2:
                if((isLeapYear == true && day > 29) || (isLeapYear == false && day > 28)){
                    return false;
                }
                break;
            default: //the months with 31 days
                if(day > 31){
                    return false;
                }
                break;
           }
        
        
      */
    }
}
