
import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static int[] marks = new int[5];
    static String name = "";
    static boolean entered = false;

    // Enter student details and marks
    static void enterMarks() {

        System.out.print("Enter student name: ");
        name = sc.nextLine();

        for (int i = 0; i < 5; i++) {

            while (true) {
                System.out.print("Enter marks for Subject " + (i + 1) + ": ");
                int m = sc.nextInt();

                if (m >= 0 && m <= 100) {
                    marks[i] = m;
                    break;
                } else {
                    System.out.println(
                        "Invalid marks. Enter between 0 and 100."
                    );
                }
            }
        }

        entered = true;
        calculate();
    }

    // Calculate total, percentage and grade
    static void calculate() {

        int total = 0;

        for (int i = 0; i < 5; i++) {
            total += marks[i];
        }

        double percentage = total / 5.0;
        String grade;

        if (percentage >= 90)
            grade = "A+";
        else if (percentage >= 75)
            grade = "A";
        else if (percentage >= 60)
            grade = "B";
        else if (percentage >= 40)
            grade = "C";
        else
            grade = "F";

        System.out.println("\nStudent: " + name);
        System.out.println("Total: " + total + "/500");
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Grade: " + grade);
    }

    // Find highest and lowest marks
    static void highestLowest() {

        int high = marks[0];
        int low = marks[0];
        int highSub = 1;
        int lowSub = 1;

        for (int i = 1; i < 5; i++) {

            if (marks[i] > high) {
                high = marks[i];
                highSub = i + 1;
            }

            if (marks[i] < low) {
                low = marks[i];
                lowSub = i + 1;
            }
        }

        System.out.println("Highest: Subject " + highSub + " (" + high + ")");
        System.out.println("Lowest: Subject " + lowSub + " (" + low + ")");
    }

    // Sort marks in descending order
    static void sortMarks() {

        int[] arr = marks.clone();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4 - i; j++) {

                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("Marks from high to low:");

        for (int i = 0; i < 5; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    // Search a particular mark
    static void searchMarks() {

        System.out.print("Enter mark to search: ");
        int search = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < 5; i++) {

            if (marks[i] == search) {
                System.out.println(
                    "Mark found in Subject " + (i + 1)
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("Mark not found.");
        }
    }

    // Save student data in file
    static void saveData() {

        if (!entered) {
            return;
        }

        try {
            FileWriter fw = new FileWriter("student.txt");

            fw.write(name + "\n");

            for (int i = 0; i < 5; i++) {
                fw.write(marks[i] + " ");
            }

            fw.close();

            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            System.out.println("File error.");
        }
    }

    // Load saved data from file
    static void loadData() {

        try {
            File file = new File("student.txt");

            if (file.exists()) {

                Scanner fs = new Scanner(file);

                if (fs.hasNextLine()) {
                    name = fs.nextLine();

                    for (int i = 0; i < 5; i++) {
                        if (!fs.hasNextInt()) {
                            fs.close();
                            return;
                        }
                        marks[i] = fs.nextInt();

                        if (marks[i] < 0 || marks[i] > 100) {
                            fs.close();
                            return;
                        }
                    }

                    entered = true;
                    System.out.println("Previous data loaded.");
                }

                fs.close();
            }

        } catch (Exception e) {
            System.out.println("Unable to load data.");
        }
    }

    // Main method
    public static void main(String[] args) {

        loadData();

        while (true) {

            System.out.println("\n===== MARKS & GRADE CALCULATOR =====");
            System.out.println("1. Enter Marks");
            System.out.println("2. Calculate Result");
            System.out.println("3. Highest and Lowest");
            System.out.println("4. Sort Marks");
            System.out.println("5. Search Marks");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    enterMarks();
                    break;

                case 2:
                    if (entered)
                        calculate();
                    else
                        System.out.println("Enter marks first.");
                    break;

                case 3:
                    if (entered)
                        highestLowest();
                    else
                        System.out.println("Enter marks first.");
                    break;

                case 4:
                    if (entered)
                        sortMarks();
                    else
                        System.out.println("Enter marks first.");
                    break;

                case 5:
                    if (entered)
                        searchMarks();
                    else
                        System.out.println("Enter marks first.");
                    break;

                case 6:
                    saveData();
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
