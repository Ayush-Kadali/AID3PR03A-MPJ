import java.util.Scanner;
import java.util.ArrayList;

class Employee {
    protected int empId;
    protected String name;
    protected double baseSalary;

    public Employee(int empId, String name, double baseSalary) {
        this.empId = empId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public void displaySalary() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: Rs. " + baseSalary);
    }
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(int empId, String name, double baseSalary) {
        super(empId, name, baseSalary);
    }

    public double calculateSalary() {
        double hike = baseSalary * 0.50;
        return baseSalary + hike;
    }

    public void displaySalary() {
        System.out.println("\nFull Time Employee");
        super.displaySalary();
        System.out.println("Hike: 50%");
        System.out.println("Salary Before Hike: Rs. " + baseSalary);
        System.out.println("Salary After Hike: Rs. " + calculateSalary());
    }
}

class InternEmployee extends Employee {

    public InternEmployee(int empId, String name, double baseSalary) {
        super(empId, name, baseSalary);
    }

    public double calculateSalary() {
        double hike = baseSalary * 0.25;
        return baseSalary + hike;
    }

    public void displaySalary() {
        System.out.println("\nIntern Employee");
        super.displaySalary();
        System.out.println("Hike: 25%");
        System.out.println("Salary Before Hike: Rs. " + baseSalary);
        System.out.println("Salary After Hike: Rs. " + calculateSalary());
    }
}

public class EmployeeInheritance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Assignment 2: Hierarchical Inheritance");
        System.out.println("Base Class: Employee");
        System.out.println("Derived Classes: FullTimeEmployee, InternEmployee\n");

        ArrayList<FullTimeEmployee> fullTimeList = new ArrayList<FullTimeEmployee>();
        ArrayList<InternEmployee> internList = new ArrayList<InternEmployee>();

        fullTimeList.add(new FullTimeEmployee(101, "Rajesh Kumar", 50000));
        fullTimeList.add(new FullTimeEmployee(102, "Sneha Patel", 60000));
        fullTimeList.add(new FullTimeEmployee(103, "Vikram Singh", 55000));

        internList.add(new InternEmployee(201, "Amit Sharma", 15000));
        internList.add(new InternEmployee(202, "Priya Gupta", 20000));

        System.out.println("Existing Full Time Employees (50% Hike)");
        for (int i = 0; i < fullTimeList.size(); i++) {
            fullTimeList.get(i).displaySalary();
        }

        System.out.println("\n\nExisting Intern Employees (25% Hike)");
        for (int i = 0; i < internList.size(); i++) {
            internList.get(i).displaySalary();
        }

        System.out.println("\n\nAdding New Employees");

        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
            System.out.print("\nEnter Employee Type (1 for Full-Time, 2 for Intern): ");
            int type = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Base Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            if (type == 1) {
                FullTimeEmployee newEmp = new FullTimeEmployee(id, name, salary);
                fullTimeList.add(newEmp);
                newEmp.displaySalary();
            } else {
                InternEmployee newEmp = new InternEmployee(id, name, salary);
                internList.add(newEmp);
                newEmp.displaySalary();
            }

            System.out.print("\nDo you want to add another employee? (y/n): ");
            choice = sc.nextLine();
        }

        System.out.println("\n\nSalary Summary");

        System.out.println("\nFull Time Employees (50% Hike):");
        double totalFullTime = 0;
        for (int i = 0; i < fullTimeList.size(); i++) {
            FullTimeEmployee emp = fullTimeList.get(i);
            System.out.println(emp.name + ": Before = Rs. " + emp.baseSalary + ", After = Rs. " + emp.calculateSalary());
            totalFullTime = totalFullTime + emp.calculateSalary();
        }

        System.out.println("\nIntern Employees (25% Hike):");
        double totalIntern = 0;
        for (int i = 0; i < internList.size(); i++) {
            InternEmployee emp = internList.get(i);
            System.out.println(emp.name + ": Before = Rs. " + emp.baseSalary + ", After = Rs. " + emp.calculateSalary());
            totalIntern = totalIntern + emp.calculateSalary();
        }

        System.out.println("\nTotal Full Time Employees: " + fullTimeList.size());
        System.out.println("Total Intern Employees: " + internList.size());
        System.out.println("Total Monthly Payroll: Rs. " + (totalFullTime + totalIntern));

        sc.close();
        System.out.println("\nProgram End");
    }
}
