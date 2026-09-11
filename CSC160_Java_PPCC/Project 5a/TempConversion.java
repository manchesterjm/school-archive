//josh mancheseter
//csc160
//project 5a F,C,K converter


import java.util.Scanner;

public class TempConversion {

   public static int	showMenu() { // displays the user menu

   	//method	variables
      Scanner input;
      int userInput;

   	//intialize	method variables
      input	= new	Scanner(System.in);
      userInput =	1;

      System.out.println("Select one of the following options:");
      System.out.println("   1. Convert Fahrenheit to Celsius (Centigrade)");
      System.out.println("   2. Convert Fahrenheit to Kelvin");
      System.out.println("   3. Display data");
      System.out.println("   4. Quit");
      System.out.print("Enter selection: ");
      userInput =	input.nextInt();
      System.out.println();

      while(userInput <	1 || userInput	> 4) { //if	user makes a choice not	on	the menu, loop	until	a correct selection is made
         System.out.printf("The selection is invalid.\n");
         System.out.print("Re-Enter selection: ");
         userInput =	input.nextInt();
         System.out.println();
      }
      return userInput;
   }

   public static void showCelsius(double f, double[][] list) {	//	convert fahrenheit to celcius

   	//method	variables
      double C;
      double K;
      int i;

   	//initialize method variables
      C = (5.0	/ 9.0) *	(f	- 32);
      K = C	+ 273.15;
      i = 0;

      if(K < 0) {	//if temp does	not exist
         System.out.printf("%.1f Fahrenheit = %.1f Kelvin which does not exist\n\n", f, K);
      }
      else { //if	temp exists
         for(i	= 0; i <	list.length; i++)	{
            if(list[i][0] == -500)	{ // check that the array does not have a	user enterd	value
               list[i][0] = f;
               list[i][1] = C;
               list[i][2] = K;
               break; // terminate loop when	data is entered into	an	"empty"	slot
            }
         }
         System.out.printf("   %.1f Fahrenheit = %.1f Celsius\n\n", f, C);	//	print	entered temp and converted	temp
      }
   }

   public static void showKelvin(double f, double[][]	list)	{ // convert fahrenheit	to	kelvin

   	//method	variables
      double C;
      double K;
      int i;

   	//initialize method variables
      C = (5.0	/ 9.0) *	(f	- 32);
      K = C	+ 273.15;
      i = 0;
      if(K < 0) {	//	if	the temp	does not	exist
         System.out.printf("%.1f Fahrenheit = %.1f Kelvin which does not exist\n\n", f, K);
      }
      else { // if temp	exists
         for(i	= 0; i <	list.length; i++)	{
            if(list[i][0] == -500)	{ // check that the array does not have a	user enterd	value
               list[i][0] = f;
               list[i][1] = C;
               list[i][2] = K;
               break; // terminate loop when	data is entered into	an	"empty"	slot
            }
         }
         System.out.printf("   %.1f Fahrenheit = %.1f Kelvin\n\n", f, K); // print entered temp	and converted temp
      }
   }

   public static void printData(double[][] list) {	//prints	temperature	data stored	in	array

   	//method	variables
      int i;
      int j;

   	//initialize method variables
      i = 0;
      j = 0;

      System.out.printf("Data Entered:  ");
      for(i	= 0; i <	list.length; i++)	{ // print header
         if(list[i][0] != -500) { // only print header if a user entered value exists
            System.out.printf("%6d", (i +	1));
         }
      }
      System.out.printf("\n");
      for(i	= 0; i <	list[i].length; i++)	{
         if(i == 0) {
            System.out.printf("%-15s",	"   Fahrenheit:");
         }
         if(i == 1) {
            System.out.printf("%-15s",	"   Celsius:");
         }
         if(i == 2) {
            System.out.printf("%-15s",	"   Kelvin:");
         }
         for(j	= 0; j <	list.length; j++)	{ // prints	out data	by	data type
            if(list[j][i] != -500) { // only print data if a user entered value exists
               System.out.printf("%6.1f",	list[j][i]);
            }
         }
         System.out.printf("\n");
      }
      System.out.printf("\n");
   }

   public static void main(String[]	args)	{	//	main method

   	//main variables
      Scanner inputMain; // used	to	get user	input
      int userSelection; // menu	selection
      int row;
      int col;
      double temp; // temperature
      boolean test1;	//	for denoting if 10 temps have	been entered or not
      boolean test2;	//	for testing	conditions in while loop
      double[][] tempChart; // 2d array for storing temperatures

   	//initialize variables
      inputMain =	new Scanner(System.in);
      userSelection = 0;
      row = 0;
      col = 0;
      temp = 0.0;
      test1	= true;
      test2	= true;
      tempChart =	new double[10][3]; // fill	array	of	10	x 3 with	default data (0)

      for(row = 0; row < tempChart.length; row++) {         // fill array with values outside the range of possible real world temperatures
         for(col = 0; col < tempChart[col].length; col++) { // used to test if user data has been entered into the arrary or if the element is "empty" of user data
            tempChart[row][col] = -500;                     // set temp to -500 F which is not a temperature that exists in the real world
         }
      }

      while(test2) {//loop	until	user terminates program
         if((tempChart[9][0] != -500) && (test1 == true))	{ // disable the ability to enter in new temps if 10 have been	entered by checking if the last element has user data entered
            System.out.printf("****The maximum amount of temperatures have been entered****\n****Entering further temperatures has been disabled****\n\n");
            test1	= false; // change to false so this warning is only dispayed once
         }
         userSelection = showMenu();
         if((userSelection	==	1 || userSelection == 2) && test1) { // test	if	a conversion is selected from	menu and	that 10 temps have not been entered
            System.out.printf("Enter the temperature to convert: ");
            temp = inputMain.nextDouble();
            if(userSelection == 1) {
               showCelsius(temp,	tempChart);//send	temp to be converted	to	Celsius
            }
            if(userSelection == 2) {
               showKelvin(temp, tempChart);//send temp to be converted to Kelvin
            }
         }
         if(userSelection == 3) { // user	wants	to	print	out the data entered	into the	array
            printData(tempChart);
         }
         if(userSelection == 4) { // user	wants	to	quit program
            test2	= false;	//	set test	condition to false to end while loop
         }
      }
   }
}