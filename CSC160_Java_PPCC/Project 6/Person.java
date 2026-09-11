//josh manchester
//csc160
//Chap10Program
//Person Class


public class Person {

   // Person class variables
   private String name;
   private String address;
   private String phone_number;
   private String e_mail_address;

   // Person class constructors
   public Person() {
      this.name = "unknown";
      this.address = "unknown";
      this.phone_number = "unknown";
      this.e_mail_address = "unknown";
   }

   public Person(String name, String address, String phone_number, String e_mail_address) {
      this.name = name;
      this.address = address;
      this.phone_number = phone_number;
      this.e_mail_address = e_mail_address;
   }

   // Person class setters
   public void setName(String name) {
      this.name = name;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public void setPhone_number(String phone_number) {
      this.phone_number = phone_number;
   }

   public void setE_mail_address(String e_mail_address) {
      this.e_mail_address = e_mail_address;
   }

   // Person class getters
   public String getName() {
      return name;
   }

   public String getAddress() {
      return address;
   }

   public String getPhone_number() {
      return phone_number;
   }

   public String getE_mail_address() {
      return e_mail_address;
   }

   // Person class methods
   public String toString() {
      String info;
      info = "\nName: " + name + "\nAddress: " + address + "\nPhone number: " + phone_number + "\nE-mail: " + e_mail_address;
      return info;
   }

}