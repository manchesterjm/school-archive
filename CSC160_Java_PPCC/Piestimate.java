//name: josh manchester
//date: 1 Oct 2019
//project: project 3

import java.util.Scanner;

public class PiEstimate {

   public static void printHeader() { // Prints out a pretty header
   
      System.out.printf("%s %12s\n", "i", "m(i)"); // format the header i will print out left justified and m(i) prints out left justified with 12 spaces from where i ends
      System.out.println("----------------"); // oh pretty dashes
   }
   
   public static void piEstimate() {
   
      // set variables ********************
      double x = 1;
      double i = 1;
      double pi = 0;
      String why = "";
      String s = "3.1415"; // set a string to check calculations by
      // ***********************************
      
      //I did not round the value of pi.  I wanted to see when the formula would spit out a value for pi that had substring of 3.1415 in it.
      
      while (!(s.equals(why))) { // set a loop to run until the calculated pi is at the right value

         pi = pi + 4 * ( Math.pow(-1, i + 1) / ((2 * i) - 1)); // calculate pi using Leibniz's Series (infintie series)
         x = x * -1; // flip x to + or - value
         why = Double.toString(pi); // change double to string to do comparisons with
         why = why + "000"; // add 000 so that the first 4.0 has 6 characters for the substring
         why = why.substring(0, 6); // take the first 6 characters of the substring, should look like 0.0000
         printData(i, why); // send info to be printed to screen
         i = i + 1; // dont forget to increment i
      }
   }
   
   public static void printData(double i, String pi) { // prints data sent to it
   
      // set variables ******************************
      String Api = "3.1415"; // for checking when pi is the correct value
      // ****************************************


      if (i % 100 == 1) { // checks if i is a mulitple of 100 + 1 (+1 so it catches the intial calculation of 4.0000) and if so starts the print routine
      
         if (i == 1) {
            System.out.printf("%.0f %14s\n", i, pi);
         }
         
         if ((i > 1) && (i < 1000)) {
            System.out.printf("%.0f %12s\n", i, pi);
         }
         
         if ((i >= 1000) && (i < 10000)) {         
            System.out.printf("%.0f %11s\n", i, pi);
         }
         
         if (i >= 10000) {
            System.out.printf("%.0f %10s\n", i, pi);
         }
      }
      
      if (Api.equals(pi)) { // prints the last line when pi is the correct value
         System.out.printf("%.0f %10s", i, pi);
      }
   }
   
   public static void main(String[] args) {
   
      // main body of the program... not much to see here ya?
      printHeader();
      piEstimate();
   }
}
