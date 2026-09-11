import java.util.Scanner;

public class ChapFourQuiz {
   public static void main(String[] args) {
   Scanner input = new Scanner(System.in);
   int F = 0;
   int i = 3434; double d = 3434.0;
   String Str = ("Welcome to Java");
   String Str2 = ("Welcom to Java");
   Character x = new Character('a');
   if (Str.substring(0).equals("Java")) {
      System.out.println("Yes");
   }
   if (Str.endsWith("Java")) {
      System.out.println("Yes 1");
   }
   //if (Str.lastIndexOf(Str2) = 0) {
     // System.out.println("Yes");
   //}
   if (Str.substring(Str.length()).equals("Java")) {
      System.out.println("Yes 2");
   }
   System.out.println(Math.cos(Math.PI));
   System.out.printf("%3.1e\n", 1234.56);
   System.out.println("Java " + 1 + 2 + 3);
   F = "abc".compareTo("aba");
   System.out.println(F);
   System.out.println(Math.floor(3.6));
   System.out.println("A" + 1);
   System.out.printf("%5d\n", 123456);
   System.out.println(Math.rint(3.6));
   System.out.println(Str.equals(Str2) == Str2.equals(Str));
   System.out.println(x.equals('a'));
   System.out.println(Math.toRadians(30));
   System.out.printf("%5d %5.1f", i, d);
   }
}