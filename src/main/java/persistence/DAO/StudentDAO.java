package persistence.DAO;

import persistence.DTO.AdminDTO;
import persistence.DTO.StudentDTO;
import persistence.DAO.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends UserDAO {

    public List<StudentDTO> findAll() {

        connect();

        List<StudentDTO> stdList = new ArrayList<>();
        String query = "SELECT * FROM USERS JOIN STUDENTS WHERE USERS.user_id = STUDENTS.user_id;";

        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            StudentDTO stdDTO;

            while(rs.next()) {

                Long userId = rs.getLong("user_id");
                String studentId = rs.getString("student_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone_number");
                int grade = rs.getInt("grade");
                int departmentId = rs.getInt("department_id");

                stdDTO = new StudentDTO(
                        userId,
                        name,
                        password,
                        phoneNumber,
                        studentId,
                        grade,
                        departmentId
                );

                stdList.add(stdDTO);

            }

        }

        catch (SQLException throwables) { throwables.printStackTrace(); }

        closeResultSet();
        closeStmt();
        closeConnection();

        return stdList;

    }

    public boolean insertStudent(StudentDTO stdDTO) {

        String query = "INSERT INTO STUDENTS (user_id, student_id, grade, department_id) VALUES (?, ?, ?, ?);";
        int changedRows = 0;

        Long userId = insertUser(stdDTO);
        if(userId != -1) stdDTO.setUserId(userId);

        connect();

        try {

            pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, userId);
            pstmt.setString(2, stdDTO.getStudentId());
            pstmt.setInt(3, stdDTO.getGrade());
            pstmt.setInt(4, stdDTO.getDepartmentId());

            changedRows = pstmt.executeUpdate();

        }
        catch (SQLException throwables) { throwables.printStackTrace(); }

        closeResultSet();
        closePstmt();
        closeConnection();

        if(changedRows == 1) return true;
        else return false;

    }

    public StudentDTO findByStudentId(String studentId) {

        connect();

        StudentDTO studentDTO;
        String query = "SELECT * FROM STUDENTS JOIN USERS WHERE STUDENTS.user_id = USERS.user_id AND student_id = ?;";

        try {

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);

            rs = pstmt.executeQuery();

            if(rs.next()) {

                Long userId = rs.getLong("user_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone_number");
                String stdId = rs.getString("student_id");
                int departmentId = rs.getInt("department_id");
                int grade = rs.getInt("grade");

                studentDTO = new StudentDTO(
                        userId,
                        name,
                        password,
                        phoneNumber,
                        stdId,
                        departmentId,
                        grade
                );

                return studentDTO;
            }

        } catch (SQLException throwables) { throwables.printStackTrace(); }

        closeResultSet();
        closePstmt();
        closeConnection();

        return null;

    }






}
