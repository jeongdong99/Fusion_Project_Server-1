package service;

import persistence.DAO.ProfessorDAO;
import persistence.DTO.AdminDTO;
import persistence.DTO.ProfessorDTO;

import java.util.List;

public class ProfessorService {

    private final ProfessorDAO professorDAO;

    public ProfessorService(ProfessorDAO professorDAO) {this.professorDAO = professorDAO;}

    public boolean insertProfessor(ProfessorDTO professorDTO) {

        boolean flag = professorDAO.isDuplicatedNumber(professorDTO.getPhoneNumber());

        if (flag == true) {
            System.out.println("전화번호 중복");
            return false;
        }

        ProfessorDTO dto = professorDAO.findByProfessorId(professorDTO.getProfessorId());

        if (dto != null) {
            System.out.println("Professor ID 중복");
            return false;
        };

        return professorDAO.insertProfessor(professorDTO);

    }

    public List<ProfessorDTO> findAll() {

        return professorDAO.findAll();

    }

    public int updatePhoneNumber(ProfessorDTO pfDTO) {

        int result = 0;
        ProfessorDTO tmpDTO = professorDAO.findByProfessorId(pfDTO.getProfessorId());

        if(tmpDTO == null) return result;

        Long userId = tmpDTO.getUserId();
        pfDTO.setUserId(userId);
        result = professorDAO.updatePhoneNumber(pfDTO);

        return result;
    }

    public boolean login(String id, String password){
        ProfessorDTO professorDTO = professorDAO.findByProfessorId(id);

        if(professorDTO == null){
            return false;
        }else{
            if(professorDTO.getPassword().equals(password)){
                return true;
            }else{
                return false;
            }
        }
    }
}
