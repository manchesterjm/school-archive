public class Chap9program {
   public static void main(String[] args) {
      // Create Polygon Objects
      RegularPolygon polyOne = new RegularPolygon();
      RegularPolygon polyTwo = new RegularPolygon(6, 4);
      RegularPolygon polyThr = new RegularPolygon(6, 4, 5.6, 7.8);
 
      // print out results
      System.out.printf("%20s %9s\n", "Perimeter", "Area");
      System.out.printf("Polygon 1 %10.2f %9.2f\n", polyOne.getPerimeter(), polyOne.getArea());
      System.out.printf("Polygon 2 %10.2f %9.2f\n", polyTwo.getPerimeter(), polyTwo.getArea());
      System.out.printf("Polygon 3 %10.2f %9.2f\n", polyThr.getPerimeter(), polyThr.getArea());
   }
}