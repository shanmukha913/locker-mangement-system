import java.util.Scanner;
abstract class User {
    protected String name;
    protected int id;

    // Constructor to set name and id
    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Abstract method for authentication
    abstract boolean authenticate(int enteredId);
}
class Student extends User {

    // Constructor that sets the name and id
    public Student(String name, int id) {
        super(name, id);
    }

    // Implement the authenticate method
    @Override
    boolean authenticate(int enteredId) {
        return this.id == enteredId; // Check if entered ID matches the student's ID
    }
}
class Locker {
    private int lockerNumber;
    private boolean isLocked = true;
    private Student owner; // Locker has a Student (composition)

    // Constructor to initialize locker with number and owner
    public Locker(int lockerNumber, Student owner) {
        this.lockerNumber = lockerNumber;
        this.owner = owner;
    }

    // Unlock the locker if the student authenticates successfully
    public void unlock(int id) {
        if (owner.authenticate(id)) {
            isLocked = false;
            System.out.println("Locker unlocked!");
        } else {
            System.out.println("Access denied! Incorrect ID.");
        }
    }

    // Lock the locker
    public void lock() {
        isLocked = true;
        System.out.println("Locker locked.");
    }

    public boolean isLocked() {
        return isLocked;
    }
}
public class LockerManager {
    public static void main(String[] args) {
        // Create a Student object
        Student student1 = new Student("Alex", 101);

        // Create a Locker object and assign it to student1
        Locker locker1 = new Locker(1, student1);

        // Scanner for taking user input
        Scanner scanner = new Scanner(System.in);

        // Display the menu
        System.out.println("Welcome to the Locker Management System");
        System.out.println("Enter your Student ID to access your locker:");

        // Take input for authentication
        int enteredId = scanner.nextInt();

        // Try to unlock the locker
        locker1.unlock(enteredId);

        // If unlocked, offer locking functionality
        if (!locker1.isLocked()) {
            System.out.println("Do you want to lock your locker? (y/n)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("y")) {
                locker1.lock();
            }
        }

        scanner.close(); // Close the scanner
    }
}
