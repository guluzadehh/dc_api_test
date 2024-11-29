package com.dc.stud_api.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.dc.stud_api.common.Result;
import com.dc.stud_api.models.Student;
import com.dc.stud_api.repository.interfaces.IStudentRepository;

@Repository
public class SqliteStudentRepository extends SqliteRepositoryContext implements IStudentRepository {
    public SqliteStudentRepository(@Value("${sqlite.db.url}") String connectionString) throws SQLException {
        super(connectionString);
    }

    @Override
    public Result<Student, RepoError> add(String fName, String lName, String surname, Date birthdate, Integer group) {
        String query = "INSERT INTO students(first_name, last_name, surname, birthdate, `group`) VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, fName);
            statement.setString(2, lName);
            statement.setString(3, surname);
            statement.setDate(4, new java.sql.Date(birthdate.getTime()));
            statement.setInt(5, group);
            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    return Result.error(RepoError.SQLITE_EXCEPTION);
                }

                Integer id = generatedKeys.getInt(1);
                Student student = new Student(fName, lName, surname, birthdate, group);
                student.setId(id);

                return Result.success(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error(RepoError.SQLITE_EXCEPTION);
        }
    }

    @Override
    public Result<?, RepoError> delete(Integer id) {
        String query = "DELETE FROM students WHERE id = ?;";

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return Result.success(null);
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error(RepoError.SQLITE_EXCEPTION);
        }
    }

    @Override
    public Result<List<Student>, RepoError> getAll() {
        List<Student> students = new ArrayList<>();

        String query = "SELECT id, first_name, last_name, surname, birthdate, `group` FROM students;";

        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("surname"),
                        resultSet.getDate("birthdate"),
                        resultSet.getInt("group"));
                student.setId(resultSet.getInt("id"));
                students.add(student);
            }

            return Result.success(students);
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error(RepoError.SQLITE_EXCEPTION);
        }
    }

    @Override
    public Result<Student, RepoError> findById(Integer id) {
        String query = "SELECT id, first_name, last_name, surname, birthdate, `group` FROM students WHERE id = ?;";

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Result.error(RepoError.STUDENT_NOT_FOUND);
                }

                Student student = new Student(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("surname"),
                        resultSet.getDate("birthdate"),
                        resultSet.getInt("group"));
                student.setId(resultSet.getInt("id"));

                return Result.success(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Result.error(RepoError.SQLITE_EXCEPTION);
        }
    }

}
