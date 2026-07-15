package service;

import Model.Student;
import dao.StudentDaoImp;

import java.sql.SQLException;

public class StudentService {

    StudentDaoImp studentDaoImp = new StudentDaoImp();

    //Register
    public void addStudent(Student student) throws SQLException {
          studentDaoImp.addStudents(student);
    }

    //Find
    public boolean viewAllStudent() throws SQLException {
        return  studentDaoImp.viewAllStudent();
    }

    //Update Marks
    public boolean updateStudent(String checkId, double marks) throws Exception {
         return studentDaoImp.updateStudent(checkId, marks);
    }

    //check exit or not
    public Student verifyStudentId(String checkId) throws Exception {
        return studentDaoImp.verifyStudentId(checkId);
    }

    public boolean findById(int id) throws SQLException {
        return  studentDaoImp.findById(id);
    }

    public boolean deleteStudent(int id) throws SQLException {
       return studentDaoImp.deleteStudent(id);
    }
}
