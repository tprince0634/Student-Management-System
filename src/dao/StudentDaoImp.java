package dao;

import Model.Student;
import Util.DatabaseConnection;

import java.sql.*;
import static Util.DatabaseConnection.getConnection;


public class StudentDaoImp implements  StudentDao {

    static StudentDaoImp studentDaoImp = new StudentDaoImp();

    @Override
    public void addStudents(Student student) throws SQLException {
        try (Connection connection = getConnection()) {
            String sql_query = "Insert into student Values(?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getCourse());
            statement.setDouble(4, student.getMarks());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Data Added Successfully ");
            } else {
                System.out.println("Insertion Failed ");
            }
        }
    }

    @Override
    public  boolean viewAllStudent() throws SQLException {

        try (Connection connection = DatabaseConnection.getConnection()) {

            String sql = "SELECT * FROM student";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (!result.next()) {
                return false;
            }

            do {
                int id = result.getInt(1);
                String name = result.getString(2);
                String course = result.getString(3);
                double marks = result.getDouble(4);

                System.out.printf(
                        "ID: %d | Name: %s | Course: %s | Marks: %.2f%n",
                        id, name, course, marks
                );

            } while (result.next());

            return true;
        }
    }

    @Override
    public Student verifyStudentId(String checkId) throws Exception{
        try(Connection con = DatabaseConnection.getConnection()){

            String query = "select * from student where id = ?";
            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setString(1, checkId);
                ResultSet result = ps.executeQuery();;
                if(result.next()){
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    String course = result.getString(3);
                    double marks = result.getDouble(4);
                    return  new Student(id,name,course,marks);
                }else {
                    System.out.println("This ID Do Not Exists ");
                }
            }

        }
        return null;
    }

    @Override
    public boolean updateStudent(String checkId, double marks) throws Exception {
        try(Connection con = DatabaseConnection.getConnection()){
            String query = "update student set marks = ? where id = ?";
            try(PreparedStatement ps = con.prepareStatement(query)){
                ps.setDouble(1, marks);
                ps.setString(2, checkId);
                int row = ps.executeUpdate();
                return row > 0;
            }
        }

    }

    @Override
    public boolean findById(int id) throws SQLException {
        try(Connection connection = DatabaseConnection.getConnection()) {
            String sql = """
                    select * from 
                    student where id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int studentid = result.getInt(1);
                String name = result.getString(2);
                String course = result.getString(3);
                double marks = result.getDouble(4);

                System.out.printf(
                        "ID: %d | Name: %s | Course: %s | Marks: %.2f%n",
                        studentid, name, course, marks
                );
                return true;
            }
            return false;
        }

    }

    @Override
    public boolean deleteStudent(int id) throws SQLException {
        try(Connection connection = DatabaseConnection.getConnection()){
            String sql = """
                    delete from student 
                    where id = ?
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            int row = statement.executeUpdate();

            if(row > 0){
                return true;
            }
            return false;
        }
    }

/*Your SQL is:
SELECT * FROM student WHERE id =
If id is a primary key, the database guarantees:
0 rows (not found), or
1 row (found)
It can never return 2 or more rows with the same ID.
That's why if expresses your intent better.*/

}
