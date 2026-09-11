//josh manchester
//csc160
//project 6
//Staff class

public class Staff extends Employee {

   // Staff class variables
   private String title;

   // Staff class constructors
   public Staff() {
      this.title = "unknown";
   }

   public Staff(String name, String address, String phone_number, String e_mail_address, String office, double salary, String title) {
      super(name, address, phone_number, e_mail_address, office, salary);
      this.title = title;
   }

   // Staff class setters
   public void setTitle(String title) {
      this.title = title;
   }

   // Staff class getters
   public String getTitle() {
      return title;
   }

   // Staff class methods
   public String toString() {
      String info;
      info = super.toString() + "\nTitle: " + title;
      return info;
   }
}
