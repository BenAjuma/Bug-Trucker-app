import java.util.ArrayList;
import java.util.Scanner;

class Bug {
    private int id;
    private String title;
    private String description;
    private String status; // e.g., "Open", "In Progress", "Resolved"

    public Bug(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bug ID: " + id + "\nTitle: " + title + "\nDescription: " + description + "\nStatus: " + status;
    }
}

public class BugTracker {
    private static ArrayList<Bug> bugList = new ArrayList<>();
    private static int bugIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBug Tracker");
            System.out.println("1. Add Bug");
            System.out.println("2. View All Bugs");
            System.out.println("3. Update Bug Status");
            System.out.println("4. Delete Bug");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBug(scanner);
                    break;
                case 2:
                    viewBugs();
                    break;
                case 3:
                    updateBugStatus(scanner);
                    break;
                case 4:
                    deleteBug(scanner);
                    break;
                case 5:
                    System.out.println("Exiting Bug Tracker. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addBug(Scanner scanner) {
        System.out.print("Enter bug title: ");
        String title = scanner.nextLine();
        System.out.print("Enter bug description: ");
        String description = scanner.nextLine();
        Bug bug = new Bug(bugIdCounter++, title, description, "Open");
        bugList.add(bug);
        System.out.println("Bug added successfully!");
    }

    private static void viewBugs() {
        if (bugList.isEmpty()) {
            System.out.println("No bugs found.");
            return;
        }
        for (Bug bug : bugList) {
            System.out.println(bug);
            System.out.println("--------------");
        }
    }

    private static void updateBugStatus(Scanner scanner) {
        System.out.print("Enter bug ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (Bug bug : bugList) {
            if (bug.getId() == id) {
                System.out.print("Enter new status (Open, In Progress, Resolved): ");
                String status = scanner.nextLine();
                bug.setStatus(status);
                System.out.println("Bug status updated successfully!");
                return;
            }
        }
        System.out.println("Bug with ID " + id + " not found.");
    }

    private static void deleteBug(Scanner scanner) {
        System.out.print("Enter bug ID to delete: ");
        int id = scanner.nextInt();
        for (int i = 0; i < bugList.size(); i++) {
            if (bugList.get(i).getId() == id) {
                bugList.remove(i);
                System.out.println("Bug deleted successfully!");
                return;
            }
        }
        System.out.println("Bug with ID " + id + " not found.");
    }
}
