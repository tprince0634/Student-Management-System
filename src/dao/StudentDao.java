package dao;

import Model.Student;

import java.sql.SQLException;

public interface StudentDao {
    public void addStudents(Student student) throws SQLException;

    public boolean viewAllStudent() throws SQLException;

    Student verifyStudentId(String checkId) throws Exception;

    boolean updateStudent(String checkId, double marks) throws Exception;

    boolean findById(int id) throws SQLException;

    boolean deleteStudent(int id) throws SQLException;
}
