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
   
      // set variables *********************

      double i = 1;
      double pi = 0;
      String why = "";
      String s = "3.1415"; // set a string to check calculations by
      
      // ***********************************
      
      //I did not round the value of pi.  I wanted to see when the formula would spit out a value for pi that had substring of 3.1415 in it.
      
      while (!(s.equals(why))) { // set a while loop to run until the calculated pi is at the right value

         pi = pi + 4 * ( Math.pow(-1, i + 1) / ((2 * i) - 1)); // calculate pi using Leibniz's Series (infintie series)
         why = pi + "0000"; // change double pi to string pi and add 4 zeros to the end so the first 4.0 will have at least 6 chars to get a 6 char substring
         why = why.substring(0, 6); // take the first 6 characters of the substring, should look like 0.0000
         
         if (i % 100 == 1)
            printData(i, why); // send info to be printed to screen
            
         i = i + 1; // dont forget to increment i, also do not use i++ outside of loops as it is just confusing
      }
      
      i = i - 1; // end of while increments i + 1 more than we need... need to subtract 1 to get correct increment of i
      printData(i, why); // pass final values to be printed
   }
   
   public static void printData(double i, String pi) { // prints data sent to it
   
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
   
   public static void main(String[] args) {
   
      // main body of the program... not much to see here ya?
      printHeader();
      piEstimate();
   }
}
