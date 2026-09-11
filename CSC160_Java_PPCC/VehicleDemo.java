//name: kenneth w riddle
//date: 10.29.2018
//project: Inheritance demo example
//         

import java.util.*;

public class VehicleDemo 
{
   public static void main(String[] args)
   {
      Truck truck = new Truck("Ford", "Ranger", 2008, 0.5);
      Car   car1  = new Car("Honda", "Civic", 2007, true);
      Car   car2  = new Car("Ford", "Contour", 1995, false);
      
      System.out.println("Make = " + truck.getMake());
      System.out.println("Model = " + truck.getModel());
      System.out.println("Year = " + truck.getYear());
      System.out.println("Tonnage = " + truck.getTonnage());
      truck.print();
      
      car1.print();
      car2.print();

      //build array of vehicles using polymorphic concept since 
      //cars and trucks are vehicles
      Vehicle[] vehicleArray = new Vehicle[10];
      
      vehicleArray[0] = car1;
      vehicleArray[1] = truck;
      vehicleArray[2] = car2;
      
      //polymorphic reference used since all vehicles have a print
      for (Vehicle x : vehicleArray)
         if (x instanceof Car || x instanceof Truck)
            x.print();
      
      //older array approach must avoid the null array members      
      for (int i=0; i<10;i++)
      {
         if (vehicleArray[i] != null)
            vehicleArray[i].print();
      }   
      
   }//end main
}//end class