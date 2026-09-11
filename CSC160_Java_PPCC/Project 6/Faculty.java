//josh manchester
//csc160
//project 6
//Faculty class

public class Faculty extends Employee {

   // Faculty class variables
   private String office_hours;
   private String rank;

   // Faculty class constructors
   public Faculty() {
      this.office_hours = "unknown";
      this.rank = "unknown";
   }

   public Faculty(String name, String address, String phone_number, String e_mail_address, String office, double salary, String office_hours, String rank) {
      super(name, address, phone_number, e_mail_address, office, salary);
      this.office_hours = office_hours;
      this.rank = rank;
   }

   // Faculty class setters
   public void setOffice_hours(String office_hours){
      this.office_hours = office_hours;
   }

   public void setRank(String rank) {
      this.rank = rank;
   }

   // Faculty class getters
   public String getOffice_hours() {
      return office_hours;
   }

   public String getRank() {
      return rank;
   }

   // Faculty class methods
   public String toString() {
      String info;
      info = super.toString() + "\nOffice hours: " + office_hours + "\nRank : " + rank;
      return info;
   }

}
