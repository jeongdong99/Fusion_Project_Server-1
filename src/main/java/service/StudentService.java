package service;

import persistence.DAO.StudentDAO;
import persistence.DTO.ProfessorDTO;
import persistence.DTO.StudentDTO;

import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) { this.studentDAO = studentDAO; }

    public boolean insertStudent(StudentDTO studentDTO) {

        boolean flag = studentDAO.isDuplicatedNumber(studentDTO.getPhoneNumber());

        if (flag == true) {
            System.out.println("전화번호 중복");
            return false;
        }

        StudentDTO dto = studentDAO.findByStudentId(studentDTO.getStudentId());

        if (dto != null) {
            System.out.println("Student ID 중복");
            return false;
        };

        return studentDAO.insertStudent(studentDTO);

    }

    public List<StudentDTO> findAll() {

        return studentDAO.findAll();

    }

    public int updateName(StudentDTO stdDTO) {

        int result = 0;
        StudentDTO tmpDTO = studentDAO.findByStudentId(stdDTO.getStudentId());

        if(tmpDTO == null) return result;

        Long userId = tmpDTO.getUserId();
        stdDTO.setUserId(userId);
        result = studentDAO.updateName(stdDTO);

        return result;

    }

}
