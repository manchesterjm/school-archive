//name: kenneth w riddle
//date: 10.29.2018
//project:  Car inheritance example
//          Vehicle is the superclass and
//          Car is the subclass


public class Car extends Vehicle
{
   private boolean hatchBack;

   public Car(String make, String model, int year, boolean hatchBack)
   {
      super(make, model, year);
      this.hatchBack = hatchBack;
   }

   public boolean getHatchBack()
   {
      return hatchBack;
   }

   public void print()
   {
      super.print();
      System.out.println("Car HatchBack: " + hatchBack);
   }
}//end class