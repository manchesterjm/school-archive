import java.util.*;

public class Exercise10_14 {
  public static void main(String[] args) {
    MyDate date = new MyDate();
    System.out.println("year: " + date.getYear());
    System.out.println("month: " + date.getMonth());
    System.out.println("day: " + date.getDay());
    System.out.println("day of the week: " + date.getDay2());
    
    date = new MyDate(669595550000L);
    System.out.println("year: " + date.getYear());
    System.out.println("month: " + date.getMonth());
    System.out.println("day: " + date.getDay());
  }
}

class MyDate {
  private int year;
  private int month;
  private int day;
  private int day2;
  
  MyDate() {    
    GregorianCalendar date = new GregorianCalendar();
    // Find year, month, and day from date. You write your own code to replace the out-dated get methods
    year = date.get(Calendar.YEAR);
    month = date.get(Calendar.MONTH);
    day = date.get(Calendar.DAY_OF_MONTH);
    day2 = date.get(Calendar.DAY_OF_WEEK);
  }
  
  MyDate(long elapsedTime) {   
    GregorianCalendar date = new GregorianCalendar();
    date.setTimeInMillis(elapsedTime);
    // Find year, month, and day from date. You write your own code to replace the out-dated get methods
    year = date.get(Calendar.YEAR);
    month = date.get(Calendar.MONTH);
    day = date.get(Calendar.DAY_OF_MONTH);
  }
    
  MyDate(int year, int month, int day, int day2) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.day2 = day2;
  }
  
  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }
  
  public int getDay() {
    return day;
  }
  
  public int getDay2() {
    return day2;
  }
  
  public void setYear(int year) {
    this.year = year;
  }
  
  public void setMonth(int month) {
    this.month = month;
  }
  
  public void setDay(int day) {
    this.day = day;
  }  
  
  public void setDay2(int day2) {
    this.day2 = day2;
  }
}
