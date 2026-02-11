class Student {
    private int rollNumber;
    private String name;
    private String email;
    protected double[] marks = new double[5];
    String department;
    public static int studentCount = 0;

    public Student() {
        this.rollNumber = 0;
        this.name = "Unknown";
        this.email = "unknown@email.com";
        this.department = "Not Assigned";
        studentCount++;
    }

    public Student(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = name.toLowerCase().replace(" ", ".") + "@mitwpu.edu.in";
        this.department = "Not Assigned";
        studentCount++;
    }

    public Student(int rollNumber, String name, String email, String department) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = email;
        this.department = department;
        studentCount++;
    }

    public Student(Student other) {
        this.rollNumber = other.rollNumber;
        this.name = other.name;
        this.email = other.email;
        this.department = other.department;
        this.marks = other.marks.clone();
        studentCount++;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMarks(double m1, double m2, double m3, double m4, double m5) {
        marks[0] = m1;
        marks[1] = m2;
        marks[2] = m3;
        marks[3] = m4;
        marks[4] = m5;
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < marks.length; i++) {
            total = total + marks[i];
        }
        return total;
    }

    public double getPercentage() {
        return getTotal() / 5;
    }

    public String getGrade() {
        double percent = getPercentage();
        if (percent >= 90) return "A+";
        else if (percent >= 80) return "A";
        else if (percent >= 70) return "B";
        else if (percent >= 60) return "C";
        else if (percent >= 40) return "D";
        else return "F";
    }

    public boolean hasPassed() {
        return getPercentage() >= 40;
    }

    public void display() {
        System.out.println("Roll No: " + rollNumber);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Marks: " + marks[0] + ", " + marks[1] + ", " + marks[2] + ", " + marks[3] + ", " + marks[4]);
        System.out.println("Total: " + getTotal() + " | Percentage: " + getPercentage() + "% | Grade: " + getGrade());
        System.out.println("Result: " + (hasPassed() ? "PASS" : "FAIL"));
    }
}

public class StudentManagement {
    public static void main(String[] args) {
        System.out.println("Assignment 1: Classes, Objects, Methods, Constructors\n");

        System.out.println("1. Creating object using DEFAULT constructor:");
        Student s1 = new Student();
        s1.display();

        System.out.println("\n2. Creating object using PARAMETERIZED constructor (2 arguments):");
        Student s2 = new Student(101, "Ayush Kadali");
        s2.department = "Computer Science";
        s2.setMarks(85, 90, 78, 88, 92);
        s2.display();

        System.out.println("\n3. Creating object using PARAMETERIZED constructor (4 arguments):");
        Student s3 = new Student(102, "Priya Sharma", "priya@mitwpu.edu.in", "Electronics");
        s3.setMarks(92, 88, 91, 85, 89);
        s3.display();

        System.out.println("\n4. Creating object using COPY constructor:");
        Student s4 = new Student(s3);
        s4.setRollNumber(103);
        s4.setName("Rahul Verma");
        s4.display();

        System.out.println("\n5. Creating another student:");
        Student s5 = new Student(104, "Neha Gupta", "neha@mitwpu.edu.in", "Mechanical");
        s5.setMarks(75, 82, 79, 88, 85);
        s5.display();

        System.out.println("\n6. Creating one more student:");
        Student s6 = new Student(105, "Amit Kumar");
        s6.department = "Civil";
        s6.setMarks(65, 70, 68, 72, 66);
        s6.display();

        System.out.println("\nDemonstrating Encapsulation:");
        System.out.println("Getting name using getter: " + s2.getName());
        s2.setEmail("ayush.updated@mitwpu.edu.in");
        System.out.println("Email after using setter: " + s2.getEmail());

        System.out.println("\nDemonstrating Static Variable:");
        System.out.println("Total students created: " + Student.studentCount);

        System.out.println("\nDemonstrating Methods with Return Values:");
        System.out.println("s2 percentage: " + s2.getPercentage() + "%");
        System.out.println("s2 grade: " + s2.getGrade());
        System.out.println("s6 percentage: " + s6.getPercentage() + "%");
        System.out.println("s6 grade: " + s6.getGrade());

        System.out.println("\nProgram End");
    }
}
