
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SimpleTaskListManager {

    // Task class
    static class Task {
        String id;
        String title;
        String priority;
        String status;

        Task(String id, String title, String priority, String status) {
            this.id = id;
            this.title = title;
            this.priority = priority;
            this.status = status;
        }
    }

    // Node class for linked list
    static class Node {
        Task data;
        Node next;

        Node(Task data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head = null;
    int nextId = 101;

    // Add task
    void addTask(String title, String priority) {

        if (title.trim().isEmpty()) {
            System.out.println("Task title cannot be empty.");
            return;
        }

        if (!priority.equalsIgnoreCase("High")
                && !priority.equalsIgnoreCase("Medium")
                && !priority.equalsIgnoreCase("Low")) {
            System.out.println("Invalid priority. Choose High, Medium or Low.");
            return;
        }

        if (priority.equalsIgnoreCase("High"))
            priority = "High";
        else if (priority.equalsIgnoreCase("Medium"))
            priority = "Medium";
        else
            priority = "Low";

        String id = "TK" + nextId;

        Task task = new Task(id, title, priority, "Pending");
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        nextId++;

        System.out.println("Task added. ID: " + id + " | " + title
                + " | Priority: " + priority + " | Status: Pending");
    }

    // Display all tasks
    void displayAll() {

        if (head == null) {
            System.out.println("No tasks found.");
            return;
        }

        Node temp = head;
        int count = 1;

        while (temp != null) {
            System.out.println(count + ". " + temp.data.id + " | "
                    + temp.data.title + " | "
                    + temp.data.priority + " | "
                    + temp.data.status);

            temp = temp.next;
            count++;
        }
    }

    // Sort tasks by priority (Bubble Sort)
    void sortByPriority() {

        if (head == null) {
            System.out.println("No tasks to sort.");
            return;
        }

        for (Node i = head; i != null; i = i.next) {
            for (Node j = head; j.next != null; j = j.next) {

                if (priorityValue(j.data.priority)
                        < priorityValue(j.next.data.priority)) {

                    Task temp = j.data;
                    j.data = j.next.data;
                    j.next.data = temp;
                }
            }
        }

        displayAll();
    }

    // Priority values for sorting
    int priorityValue(String priority) {
        if (priority.equals("High"))
            return 3;
        else if (priority.equals("Medium"))
            return 2;
        else
            return 1;
    }

    // Mark task as done
    void markDone(String id) {

        Node temp = head;

        while (temp != null) {
            if (temp.data.id.equalsIgnoreCase(id)) {
                temp.data.status = "Done";
                System.out.println("Task " + temp.data.id + " marked Done.");
                return;
            }
            temp = temp.next;
        }

        System.out.println("Task ID not found.");
    }

    // Delete task
    void deleteTask(String id) {

        if (head == null) {
            System.out.println("No tasks found.");
            return;
        }

        if (head.data.id.equalsIgnoreCase(id)) {
            head = head.next;
            System.out.println("Task deleted.");
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            if (temp.next.data.id.equalsIgnoreCase(id)) {
                temp.next = temp.next.next;
                System.out.println("Task deleted.");
                return;
            }
            temp = temp.next;
        }

        System.out.println("Task ID not found.");
    }

    // Load tasks from file
    void loadTasks() {

        try {
            File file = new File("tasks.txt");

            if (!file.exists())
                return;

            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split("\t");

                if (parts.length == 4) {
                    Task task = new Task(parts[0], parts[1],
                            parts[2], parts[3]);
                    Node newNode = new Node(task);

                    if (head == null) {
                        head = newNode;
                    } else {
                        Node temp = head;
                        while (temp.next != null) {
                            temp = temp.next;
                        }
                        temp.next = newNode;
                    }

                    int number = Integer.parseInt(task.id.substring(2));
                    if (number >= nextId)
                        nextId = number + 1;
                }
            }

            fileReader.close();

        } catch (Exception e) {
            System.out.println("Error loading tasks.");
        }
    }

    // Save tasks to file
    void saveTasks() {

        try {
            FileWriter writer = new FileWriter("tasks.txt");
            Node temp = head;

            while (temp != null) {
                writer.write(temp.data.id + "\t"
                        + temp.data.title + "\t"
                        + temp.data.priority + "\t"
                        + temp.data.status + "\n");
                temp = temp.next;
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    // Main menu
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SimpleTaskListManager manager = new SimpleTaskListManager();

        manager.loadTasks();

        int choice = 0;

        while (choice != 6) {

            System.out.println("\n===== SIMPLE TASK LIST MANAGER =====");
            System.out.println("1. Add Task");
            System.out.println("2. Display All");
            System.out.println("3. Sort by Priority");
            System.out.println("4. Mark Done");
            System.out.println("5. Delete Task");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Title: ");
                        String title = sc.nextLine();

                        System.out.print("Priority (High/Medium/Low): ");
                        String priority = sc.nextLine();

                        manager.addTask(title, priority);
                        break;

                    case 2:
                        manager.displayAll();
                        break;

                    case 3:
                        manager.sortByPriority();
                        break;

                    case 4:
                        System.out.print("Enter Task ID: ");
                        String doneId = sc.nextLine();
                        manager.markDone(doneId);
                        break;

                    case 5:
                        System.out.print("Enter Task ID to delete: ");
                        String deleteId = sc.nextLine();
                        manager.deleteTask(deleteId);
                        break;

                    case 6:
                        manager.saveTasks();
                        System.out.println("Tasks saved. Exiting.");
                        break;

                    default:
                        System.out.println("Invalid choice. Enter 1-6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }

        sc.close();
    }
}
