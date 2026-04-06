import java.util.Scanner;

class Shape {
    protected String label;

    public Shape() {
        this.label = "Generic Shape";
    }

    public Shape(String label) {
        this.label = label;
    }

    public double area() {
        return 0.0;
    }

    public double area(double side) {
        return side * side;
    }

    public double area(double length, double breadth) {
        return length * breadth;
    }

    public double area(double base, double height, boolean triangle) {
        if (triangle) {
            return 0.5 * base * height;
        }
        return base * height;
    }

    public double area(float radius) {
        return Math.PI * radius * radius;
    }

    public void describe() {
        System.out.println("Shape: " + label);
    }
}

public class Shapes {
    public static void main(String[] args) {
        System.out.println("Assignment 3.1: Shapes - Polymorphism via Overloading\n");

        System.out.println("Built-in demo");
        System.out.println();
        Shape s1 = new Shape();
        s1.describe();
        System.out.println("Default area: " + s1.area());

        Shape square = new Shape("Square");
        square.describe();
        System.out.println("Area (side 5): " + square.area(5.0));

        Shape rect = new Shape("Rectangle");
        rect.describe();
        System.out.println("Area (8 x 3): " + rect.area(8.0, 3.0));

        Shape tri = new Shape("Triangle");
        tri.describe();
        System.out.println("Area (base 6, height 4): " + tri.area(6.0, 4.0, true));

        Shape dish = new Shape("Antenna Dish Footprint");
        dish.describe();
        float dishRadius = 1.2f;
        System.out.println("Area (radius " + dishRadius + " m): " + dish.area(dishRadius));

        Scanner sc = new Scanner(System.in);
        System.out.println("\nNow your turn. Pick a shape and enter dimensions.");
        Shape user = new Shape("User Shape");
        int choice = 0;
        do {
            System.out.println("\n1. Square     2. Rectangle     3. Triangle");
            System.out.println("4. Circle     5. Exit");
            System.out.print("Pick a shape: ");
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number please.");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Side: ");
                    double s = Double.parseDouble(sc.nextLine().trim());
                    System.out.println("Area = " + user.area(s));
                    break;
                }
                case 2: {
                    System.out.print("Length: ");
                    double l = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Breadth: ");
                    double b = Double.parseDouble(sc.nextLine().trim());
                    System.out.println("Area = " + user.area(l, b));
                    break;
                }
                case 3: {
                    System.out.print("Base: ");
                    double b = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Height: ");
                    double h = Double.parseDouble(sc.nextLine().trim());
                    System.out.println("Area = " + user.area(b, h, true));
                    break;
                }
                case 4: {
                    System.out.print("Radius: ");
                    float r = Float.parseFloat(sc.nextLine().trim());
                    System.out.println("Area = " + user.area(r));
                    break;
                }
                case 5:
                    System.out.println("Bye.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}
