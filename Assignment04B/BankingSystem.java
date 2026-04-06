import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class InvalidIdException extends Exception {
    public InvalidIdException(String msg) {
        super(msg);
    }
}

class MinBalanceException extends Exception {
    public MinBalanceException(String msg) {
        super(msg);
    }
}

class NegativeAmountException extends Exception {
    public NegativeAmountException(String msg) {
        super(msg);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}

class Customer {
    int cid;
    String cname;
    String email;
    double amount;

    public Customer(int cid, String cname, double amount) {
        this.cid = cid;
        this.cname = cname;
        this.amount = amount;
        this.email = cname.toLowerCase().replace(" ", ".") + "@mitwpu.edu.in";
    }

    public String toRecord() {
        return cid + "," + cname + "," + email + "," + amount;
    }

    public static Customer fromRecord(String line) {
        String[] parts = line.split(",");
        Customer c = new Customer(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[3]));
        c.email = parts[2];
        return c;
    }

    public void display() {
        System.out.println("CID: " + cid + " | Name: " + cname + " | Email: " + email + " | Balance: Rs. " + amount);
    }
}

public class BankingSystem {

    static final String FILE = "customers.txt";
    static final double MIN_BALANCE = 1000.0;
    static ArrayList<Customer> customers = new ArrayList<Customer>();

    public static void main(String[] args) {
        loadFromFile();

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\nMIT-WPU Banking System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display All Customers");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1: createAccount(sc); break;
                    case 2: deposit(sc); break;
                    case 3: withdraw(sc); break;
                    case 4: displayAll(); break;
                    case 5: System.out.println("Saving and exiting..."); break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (InvalidIdException | MinBalanceException | NegativeAmountException | InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            }

            saveToFile();

        } while (choice != 5);

        sc.close();
    }

    static void createAccount(Scanner sc) throws InvalidIdException, MinBalanceException, NegativeAmountException {
        System.out.print("Enter CID (1-20): ");
        int cid = Integer.parseInt(sc.nextLine().trim());
        validateId(cid);

        for (Customer c : customers) {
            if (c.cid == cid) {
                throw new InvalidIdException("CID " + cid + " already exists.");
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Initial Amount: ");
        double amt = Double.parseDouble(sc.nextLine().trim());
        validatePositive(amt);
        if (amt < MIN_BALANCE) {
            throw new MinBalanceException("Initial deposit must be at least Rs. " + MIN_BALANCE);
        }

        Customer c = new Customer(cid, name, amt);
        customers.add(c);
        System.out.println("Account created.");
        c.display();
    }

    static void deposit(Scanner sc) throws InvalidIdException, NegativeAmountException {
        System.out.print("Enter CID: ");
        int cid = Integer.parseInt(sc.nextLine().trim());
        validateId(cid);
        Customer c = findCustomer(cid);

        System.out.print("Enter amount to deposit: ");
        double amt = Double.parseDouble(sc.nextLine().trim());
        validatePositive(amt);

        c.amount += amt;
        System.out.println("Deposited. New balance: Rs. " + c.amount);
    }

    static void withdraw(Scanner sc) throws InvalidIdException, NegativeAmountException, InsufficientFundsException {
        System.out.print("Enter CID: ");
        int cid = Integer.parseInt(sc.nextLine().trim());
        validateId(cid);
        Customer c = findCustomer(cid);

        System.out.print("Enter amount to withdraw: ");
        double amt = Double.parseDouble(sc.nextLine().trim());
        validatePositive(amt);

        if (amt > c.amount) {
            throw new InsufficientFundsException("Withdrawal Rs. " + amt + " exceeds balance Rs. " + c.amount);
        }
        c.amount -= amt;
        System.out.println("Withdrawn. New balance: Rs. " + c.amount);
    }

    static void displayAll() {
        if (customers.isEmpty()) {
            System.out.println("No customers on record.");
            return;
        }
        for (Customer c : customers) c.display();
    }

    static void validateId(int cid) throws InvalidIdException {
        if (cid < 1 || cid > 20) {
            throw new InvalidIdException("CID must be between 1 and 20.");
        }
    }

    static void validatePositive(double amt) throws NegativeAmountException {
        if (amt <= 0) {
            throw new NegativeAmountException("Amount must be positive.");
        }
    }

    static Customer findCustomer(int cid) throws InvalidIdException {
        for (Customer c : customers) {
            if (c.cid == cid) return c;
        }
        throw new InvalidIdException("No customer found with CID " + cid);
    }

    static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Customer c : customers) {
                bw.write(c.toRecord());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("File save error: " + e.getMessage());
        }
    }

    static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    customers.add(Customer.fromRecord(line));
                }
            }
            System.out.println("Loaded " + customers.size() + " customer(s) from file.");
        } catch (IOException e) {
            System.out.println("No existing record file. Starting fresh.");
        }
    }
}
