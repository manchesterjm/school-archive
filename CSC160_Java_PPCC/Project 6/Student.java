//josh manchester
//csc160
//CHap10Program
//Student class


public class Student extends Person {

   // Student class variables
   private int status;
   public final static int FRESHMAN = 1;  // variables must be static to be called by a static method, in this case the "main" method which is static
   public final static int SOPHOMORE = 2; // variables are final denoting that they are unchanging from their set value
   public final static int JUNIOR = 3;
   public final static int SENOIR = 4;

   // Student class constructors
   public Student() {
      this.status = 0;
   }

   public Student(String name, String address, String phone_number, String e_mail_address, int status) {
      super(name, address, phone_number, e_mail_address);
      this.status = status;
   }

   // Student class setters
   public void setStatus(int status) {
      this.status = status;
   }

   // Student class getters
   public String getStatus() { // could have used a switch here but didn't
      String temp;
      temp = "";
      if(status == 1) {
         temp = "Freshman";
      }
      else if(status == 2) {
         temp = "Sophomore";
      }
      else if(status == 3) {
         temp = "Junoir";
      }
      else if(status == 4) {
         temp = "Senoir";
      }
      else {
         temp = "Unknown";
      }
      return temp;
   }

   // Student class methods
   public String toString() {
      String info;
      info = super.toString() + "\nStatus: " + getStatus();
      return info;
   }
}