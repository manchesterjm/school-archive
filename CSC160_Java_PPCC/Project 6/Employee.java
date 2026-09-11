//josh manchester
//csc160
//project 6
//Employee class

public class Employee extends Person {

   // Employee class variables
   private String office;
   private double salary;
   private MyDate date_hired;

   // Employee class constructors
   public Employee() {
      this.office = "unknown";
      this.salary = 0.00;
      this.date_hired = new MyDate();
   }

   public Employee(String name, String address, String phone_number, String e_mail_address, String office, double salary) {
      super(name, address, phone_number, e_mail_address);
      this.office = office;
      this.salary = salary;
      this.date_hired = new MyDate();

   }

   // Employee class setters
   public void setOffice(String office) {
      this.office = office;
   }

   public void setSalary(double salary) {
      this.salary = salary;
   }

   public void setDate_hired() {
      date_hired = new MyDate();
   }

   // Employee class getters
   public String getOffice() {
      return office;
   }

   public String getSalary() {
      String info;
      info = String.format("%.2f", salary);
      return info;
   }

   public String getDate_hired() {
      String temp;
      temp = date_hired.getDay() + " " + (date_hired.getMonth() + 1) + " " + date_hired.getYear();
      return temp;
   }

   // Employee class methods
   public String toString() {
      String info;
      info = super.toString() + "\nOffice: " + office + "\nSalary: $" + getSalary() + "\nDate hired: " + getDate_hired();
      return info;
   }
}
