package persistence.DAO;

import persistence.DTO.AdminDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// db에 접근하기 위한 용도
public class AdminDAO extends UserDAO {

    public List<AdminDTO> findAll() {

        connect();

        List<AdminDTO> adminList = new ArrayList<>();
        String query = "SELECT * FROM ADMINS JOIN USERS WHERE ADMINS.user_id = USERS.user_id;";

        try{

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            AdminDTO adminDTO;

            while(rs.next()) {

                Long userId = rs.getLong("user_id");
                String adminId = rs.getString("admin_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone_number");

                adminDTO = new AdminDTO(
                        userId,
                        adminId,
                        name,
                        password,
                        phoneNumber
                );

                adminList.add(adminDTO);

            }

        }

        catch(SQLException e){ System.out.println("error : " + e); }

        closeResultSet();
        closeStmt();
        closeConnection();

        return adminList;

    }

    public AdminDTO findByAdminId(String adminId) {

        connect();

        AdminDTO adminDTO;

        try{

            String query = "SELECT * FROM ADMINS JOIN USERS WHERE ADMINS.user_id = USERS.user_id AND admin_id = ?;";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, adminId);

            rs = pstmt.executeQuery();

            if(rs.next()) {

                Long userId = rs.getLong("user_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phone_number");

                adminDTO = new AdminDTO(
                        userId,
                        name,
                        password,
                        phoneNumber,
                        adminId
                );

                return adminDTO;


            }

        }

        catch(SQLException e){ System.out.println("error : " + e); }

        closeResultSet();
        closePstmt();
        closeConnection();

        return null;

    }

    public boolean insertAdmin(AdminDTO adminDTO) {

        String adminQuery = "INSERT INTO ADMINS (user_id, admin_id) VALUES (?, ?);";
        int changedRows = 0;

        // insert into users
        Long userId = insertUser(adminDTO);
        if(userId != -1) adminDTO.setUserId(userId);

        connect();

        // admin id 중복 조회 필요 >> service에서?
        try{

            pstmt = conn.prepareStatement(adminQuery);
            pstmt.setLong(1, adminDTO.getUserId());
            pstmt.setString(2, adminDTO.getAdminId());

            changedRows = pstmt.executeUpdate();

        }

        catch(SQLException e){
            // 롤백 로직?
            // System.out.println("admin id 중복");
            System.out.println("error : " + e);
        }

        closeResultSet();
        closePstmt();
        closeConnection();

        if(changedRows == 1) return true;
        else return false;
    }

} // end of AdminDAO Class
