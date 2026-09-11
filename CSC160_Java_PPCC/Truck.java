//name: kenneth w riddle
//date: 10.29.2018
//project: Truck inheritance example
//


public class Truck extends Vehicle
{
   private double tonnage;

   public Truck(String make, String model, int year, double tonnage)
   {
      super(make, model, year);
      this.tonnage = tonnage;
   }

   public double getTonnage()
   {
      return tonnage;
   }

   public void print()
   {
      super.print();
      System.out.println("Truck Tonnage: " + tonnage);
   }
}