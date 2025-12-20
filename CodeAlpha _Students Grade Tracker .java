import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        int n = 0;

        // SAFE input for number of students
        while (true) {
            System.out.print("Enter number of students: ");
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n > 0) break;
                System.out.println("Enter a valid number (greater than 0)");
            } catch (Exception e) {
                System.out.println("Please enter numbers only!");
            }
        }

        // Student details
        for (int i = 0; i < n; i++) {

            System.out.println("\nStudent " + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            int grade;
            while (true) {
                System.out.print("Grade: ");
                try {
                    grade = Integer.parseInt(sc.nextLine());
                    if (grade >= 0 && grade <= 100) break;
                    System.out.println("Enter grade between 0 and 100");
                } catch (Exception e) {
                    System.out.println("Numbers only!");
                }
            }

            students.add(new Student(name, grade));
        }

        int total = 0;
        int highest = students.get(0).grade;
        int lowest = students.get(0).grade;

        for (Student s : students) {
            total += s.grade;
            if (s.grade > highest) highest = s.grade;
            if (s.grade < lowest) lowest = s.grade;
        }

        double average = (double) total / students.size();

        System.out.println("\n===== Student Grade Summary =====");
        for (Student s : students) {
            System.out.println("Name: " + s.name + " | Grade: " + s.grade);
        }

        System.out.println("\nAverage Grade: " + average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);

        sc.close();
    }
}