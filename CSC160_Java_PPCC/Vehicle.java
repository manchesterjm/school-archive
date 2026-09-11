//name: kenneth w riddle
//date: 10.29.2018
//project:  Vehicle inheritance example superclass
//          All vehicles subclasses must have a print method

public class Vehicle
{
   private String make;
   private String model;
   private int year;

   public Vehicle(String make, String model, int year)
   {
      this.make = make;
      this.model = model;
      this.year = year;
   }

   public String getMake()
   {
      return make;
   }

   public String getModel()
   {
      return model;
   }

   public int getYear()
   {
      return year;
   }

   public void print()
   {
      System.out.println("Make: " + make + ", Model: " + model + ", Year: " + year);
   }
}