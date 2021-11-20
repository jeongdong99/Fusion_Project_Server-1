package service;

import persistence.DAO.AdminDAO;
import persistence.DTO.AdminDTO;

import java.util.List;

public class AdminService {

    private final AdminDAO adminDAO;

    public AdminService(AdminDAO adminDAO) { this.adminDAO = adminDAO; }

    public List<AdminDTO> findAll() {

        return adminDAO.findAll();

    }

    public AdminDTO findByAdminId(String adminId) {

        return adminDAO.findByAdminId(adminId);

    }

    public boolean insertAdmin(AdminDTO adminDTO) {

        boolean flag = adminDAO.isDuplicatedNumber(adminDTO.getPhoneNumber());

        if (flag == true) {
            System.out.println("전화번호 중복");
            return false;
        }

        AdminDTO dto = adminDAO.findByAdminId(adminDTO.getAdminId());

        if (dto != null) {
            System.out.println("Admin ID 중복");
            return false;
        };

        return adminDAO.insertAdmin(adminDTO);

    }

}
