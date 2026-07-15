package Util;

import Model.Student;
import dao.StudentDao;
import dao.StudentDaoImp;
import service.StudentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class StartUp {

    static  Scanner sc = new Scanner(System.in);
    static StudentDaoImp studentDaoImp = new StudentDaoImp();
    static StudentService studentService = new StudentService();


    public void startMethod() throws Exception {
        int choice = 0;

        while (choice != 6) {
            System.out.println(" Welcome to Student Management ");
            System.out.println("1. Register Student");
            System.out.println("2. View All Student");
            System.out.println("3. Update Student Marks");
            System.out.println("4. View Student By Id");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter Here: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerStudent();
                    break;

                case 2:
                   viewAll();
                    break;

                case 3:
                    updateStudentMarks();
                    break;

                case 4:
                   findById();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Thank you for our application");
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid Input");
                    break;

            }
        }
    }

    //Register Method
    public  static  void registerStudent() throws SQLException {
        System.out.println("Enter you Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Course Name: ");
        String course = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        Student student = new Student(generateId(),name,course,marks);
        studentService.addStudent(student);

    }

    private static int generateId() {
        Random random = new Random();
        return random.nextInt(900) + 100; // Generates 100 to 999
    }

    //viewAll
    public void viewAll() throws SQLException {
        boolean dataAvailable = studentService.viewAllStudent();

        if (dataAvailable) {
            System.out.println("Record Fetch Successfully");
        } else {
            System.out.println("No Available Records");
        }
    }

    public static void updateStudentMarks() throws Exception {
        System.out.print("Enter Student ID: ");
        String checkId = sc.nextLine();

        Student student = studentService.verifyStudentId(checkId);

        if (student != null) {
            System.out.print("Enter Update Marks: ");
            double marks = sc.nextDouble();

            boolean updated = studentService.updateStudent(checkId, marks);

            if (updated) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Update failed.");
            }
        } else {
            System.out.println("Student With This ID Does Not Exist in Our System.");
        }
    }



    //Search By id
    public void findById() throws SQLException {
        System.out.println("Enter the Student Id : ");
        int id = sc.nextInt();
        boolean found = studentService.findById(id);

        if(found){
            System.out.println("Record fetched Successfully");
        }else {
            System.out.println("This Id Does Not Exits ! ");
        }
    }

    public void deleteStudent() throws SQLException {
        System.out.println("Enter the Id :");
        int id = sc.nextInt();
        boolean deletedFound = studentService.deleteStudent(id);

        if(deletedFound){
            System.out.println("Record Deleted Successfully");
        }else {
            System.out.println("This Id Does Not Exits ! ");
        }

    }





}
