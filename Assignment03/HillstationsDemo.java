import java.util.Scanner;

class Hillstations {
    protected String name;

    public Hillstations(String name) {
        this.name = name;
    }

    public void famousfor() {
        System.out.println(name + " is a hill station in India.");
    }

    public void famousfood() {
        System.out.println(name + " offers various local cuisines.");
    }
}

class Lonavala extends Hillstations {
    public Lonavala() {
        super("Lonavala");
    }

    public void famousfor() {
        System.out.println(name + " is famous for Tiger's Leap, Karla Caves and monsoon waterfalls.");
    }

    public void famousfood() {
        System.out.println(name + " is known for Maganlal Chikki and Vada Pav.");
    }
}

class Manali extends Hillstations {
    public Manali() {
        super("Manali");
    }

    public void famousfor() {
        System.out.println(name + " is famous for Solang Valley, Rohtang Pass and adventure sports.");
    }

    public void famousfood() {
        System.out.println(name + " is known for Siddu, Trout fish and Babru.");
    }
}

class Munnar extends Hillstations {
    public Munnar() {
        super("Munnar");
    }

    public void famousfor() {
        System.out.println(name + " is famous for tea gardens, Eravikulam National Park and rolling hills.");
    }

    public void famousfood() {
        System.out.println(name + " is known for Appam with stew, Puttu and Kerala spices.");
    }
}

public class HillstationsDemo {
    public static void main(String[] args) {
        System.out.println("Assignment 3.2: Hillstations - Runtime Polymorphism\n");

        System.out.println("Walking the trip list (each call goes through a parent reference):");
        System.out.println();
        Hillstations[] trip = { new Lonavala(), new Manali(), new Munnar() };
        for (Hillstations stop : trip) {
            stop.famousfor();
            stop.famousfood();
            System.out.println();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Now your turn. Pick a hill station to learn about.");
        int choice = 0;
        do {
            System.out.println("\nPick a hill station:");
            System.out.println("1. Lonavala");
            System.out.println("2. Manali");
            System.out.println("3. Munnar");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a number please.");
                continue;
            }

            Hillstations h = null;
            switch (choice) {
                case 1: h = new Lonavala(); break;
                case 2: h = new Manali(); break;
                case 3: h = new Munnar(); break;
                case 4: System.out.println("Bye."); continue;
                default: System.out.println("Invalid choice."); continue;
            }

            h.famousfor();
            h.famousfood();
            System.out.println("(Reference type was Hillstations, runtime type was " + h.getClass().getSimpleName() + ")");
        } while (choice != 4);

        sc.close();
    }
}
