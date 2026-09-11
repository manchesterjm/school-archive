//name: josh manchester
//date: 17 Sep 2019
//project: project2
import java.util.Scanner;

public class Payroll {
   public static void main(String[] args) {
   Scanner input = new Scanner(System.in);
   String EName = "";
   double hours = 0.0;
   double payrate = 0.0;
   double FTax = 0.0;  //Federal Tax Rate
   double STax = 0.0;  //State Tax Rate
   double FedW = 0.0;  //Total Federal Taxes Withheld
   double StaW = 0.0;  //Total State Taxes Withheld
   double GrPay = 0.0;  //Gross Pay calculated as (hours * payrate) or Overtime which is normal pay calulated as (payrate * 40) + overtime pay calculated as (((hours - 40) * payrate) * OverTime)
   double TotDed = 0.0;  //Total Deductions from pay (Total Federal Taxes + Total State Taxes)
   double NetPay = 0.0;  //Net Pay due employee (Gross Pay - Total Deductions)
   double OverTime = 0.0;  //Overtime (Percentage of normal pay per hour due employee over 40 hours of working time)
   //String TotDedStr = ""; //For testing purposes
   
   System.out.print("Enter employee's name: ");
   EName = input.nextLine();
   System.out.print("Enter number of hours worked in a week (eg 40.0): ");
   hours = input.nextDouble();
   System.out.print("Enter hourly pay rate (eg 45.33): ");
   payrate = input.nextDouble();
   if (hours > 40) { //Find out what pay is due for Overtime past 40 hours a week.  Normally pay and a half.
      System.out.print("Enter Overtime percentage for hours over 40 (eg 1.5): ");
      OverTime = input.nextDouble();
      if (OverTime < 1.0) {
         OverTime = 1.0;
         System.out.println("Overtime rate entered was less than 1 meaning no extra pay for overtime.");
      }
   }
   System.out.print("Enter federal tax withholding rate (eg 0.23): ");
   FTax = input.nextDouble();
   System.out.print("Enter state tax withholding(eg 0.12): ");
   STax = input.nextDouble();
   
   if (OverTime > 1.0) {
      GrPay = (payrate * 40) + (((hours - 40) * payrate) * OverTime); //cal Gross Pay with Overtime
      //System.out.println("Test only, overtime pay is " + ((hours - 40) * payrate) * OverTime);  // For testing purposes
   }
   else {
      GrPay = payrate * hours; //cal Gross Pay without Overtime
   }
   FedW = GrPay * FTax; //cal Federal Withholding
   StaW = GrPay * STax; //cal State Withholding
   TotDed = FedW + StaW; //cal Total Deductions
   NetPay = GrPay - TotDed; //cal Net Pay
   //TotDedStr = "$" + (float) Math.round(TotDed * 100) / 100.0;  //for testing purposes
   
   System.out.printf("\n\n"); //Space two lines down for looks and readability
   System.out.printf("%-13s %s\n\n", "Employee Name:", EName);
   System.out.printf("%-13s %9.2f\n", "Hours Worked:", hours);
   System.out.printf("%-13s $%8.2f\n", "Pay Rate:", payrate);
   if (OverTime > 0.0) { //Print lines only if there was overtime so we can see what the difference in pay is
      System.out.printf("%-13s $%8.2f\n", "Overtime Pay:", ((hours - 40) * payrate) * OverTime);
      System.out.printf("%-13s $%8.2f\n", "Normal Pay:", (hours - (hours - 40)) * payrate);
   }
   System.out.printf("%-13s $%8.2f\n\n", "Gross Pay:", GrPay); //Add extra line for readability
   System.out.printf("Deductions\n");
   System.out.printf("   %-20s(%4.1f%s):%2s%8.2f\n", "Federal Withholding ", (FTax * 100), "%", "$", FedW);
   System.out.printf("   %-20s(%4.1f%s):%2s%8.2f\n", "State Withholding ", (STax * 100), "%", "$", StaW);
   System.out.printf("   %-27s:%2s%8.2f\n\n", "Total Deductions", "$", TotDed); //Add extra line for readability
   System.out.printf("Net Pay: $%8.2f", NetPay);
   }
}