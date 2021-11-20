package persistence.DAO;


import persistence.DTO.AdminDTO;
import persistence.DTO.ProfessorDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO extends UserDAO {

    public List<ProfessorDTO> findAll() {

        connect();

        List<ProfessorDTO> pfList = new ArrayList<>();
        String query = "SELECT * FROM USERS JOIN PROFESSORS WHERE USERS.user_id = PROFESSORS.user_id;";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            ProfessorDTO pfDTO;

            while(rs.next()) {

                Long userId = rs.getLong("user_id");
                String professorId = rs.getString("professor_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone_number");
                int departmentId = rs.getInt("department_id");

                pfDTO = new ProfessorDTO(
                        userId,
                        name,
                        password,
                        phoneNumber,
                        departmentId,
                        professorId
                );

                pfList.add(pfDTO);
            }
        }

        catch (SQLException throwables) { throwables.printStackTrace(); }

        closeResultSet();
        closeStmt();
        closeConnection();

        return pfList;

    }

    public boolean insertProfessor(ProfessorDTO pfDTO) {

        String query = "INSERT INTO PROFESSORS (user_id, professor_id, department_id) VALUES (?, ?, ?);";
        int changedRows = 0;

        Long userId = insertUser(pfDTO);
        if(userId != -1) pfDTO.setUserId(userId);

        connect();

        try {

            pstmt = conn.prepareStatement(query);
            pstmt.setLong(1, userId);
            pstmt.setString(2, pfDTO.getProfessorId());
            pstmt.setInt(3, pfDTO.getDepartmentId());

            changedRows = pstmt.executeUpdate();

        }
        catch (SQLException throwables) { throwables.printStackTrace(); }

        closeResultSet();
        closePstmt();
        closeConnection();

        if(changedRows == 1) return true;
        else return false;

    }

    public ProfessorDTO findByProfessorId(String professorId) {

        connect();

        ProfessorDTO professorDTO;

        String query = "SELECT * FROM PROFESSORS JOIN USERS WHERE PROFESSORS.user_id = USERS.user_id AND professor_id = ?;";

        try {

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, professorId);

            rs = pstmt.executeQuery();

            if(rs.next()) {

                Long userId = rs.getLong("user_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone_number");
                int departmentId = rs.getInt("department_id");

                professorDTO = new ProfessorDTO(
                        userId,
                        name,
                        password,
                        phoneNumber,
                        departmentId,
                        professorId
                );

                return professorDTO;

            }

        } catch (SQLException throwables) { throwables.printStackTrace(); }

        closeResultSet();
        closePstmt();
        closeConnection();

        return null;

    }

}
