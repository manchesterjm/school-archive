//josh manchester
//csc160
//project 6
//Chap10Program class

public class Chap10Program {
   public static void main(String[] args) {

      Person Jack = new Person("Jack Handy", "123 Townly Lane", "719-555-1212", "jack.handy@gmail.com");
      Student Test = new Student("Test Dummy", "72 Headcrack Ave", "719-HELP-ME2", "test.dummy@gmail.com", Student.SOPHOMORE);
      Employee John = new Employee("John Hanncock", "10 Downing St", "888-777-3456", "john.hanncock@gmail.com", "616", 75000.00);
      Faculty Barbara = new Faculty("Barbara Eden", "11 Jeenie Dreaming Way", "808-101-7777", "grant.your.wish@genie.org", "The Oval Office", 100000.00, "9am to 5pm", "Genie in charge");
      Staff Elizabeth = new Staff("Elizabeth Montgomery", "23 Witch Way", "808-101-6161", "casting.spells@warlocks_limited.net", "The Top Office", 95000.00, "Mischief Maker");
      Person a = new Person();
      Student b = new Student();
      Employee c = new Employee();
      Faculty d = new Faculty();
      Staff e = new Staff();

      System.out.println(Jack.toString());
      System.out.println(Test.toString());
      System.out.println(John.toString());
      System.out.println(Barbara.toString());
      System.out.println(Elizabeth.toString());
      System.out.println(a.toString());
      System.out.println(b.toString());
      System.out.println(c.toString());
      System.out.println(d.toString());
      System.out.println(e.toString());
      Jack.setPhone_number("111-111-1111");
      System.out.println(Jack.toString());

   }
}