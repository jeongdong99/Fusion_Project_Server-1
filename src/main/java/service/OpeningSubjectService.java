package service;

import persistence.DTO.LectureTimeTableDTO;
import persistence.MyBatisConnectionFactory;
import persistence.DAO.OpeningSubjectDAO;
import persistence.DAO.SubjectDAO;
import persistence.DTO.OpeningSubjectDTO;
import persistence.DTO.SubjectDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpeningSubjectService {

    OpeningSubjectDAO openingSubjectDAO = new OpeningSubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());
    SubjectDAO subjectDAO = new SubjectDAO((MyBatisConnectionFactory.getSqlSessionFactory()));

    public void updateRegisterTime(int grade, OpeningSubjectDTO openingSubjectDTO){

        List<SubjectDTO> list = subjectDAO.selectByGrade(grade);

        for(int i = 0; i < list.size(); i++){

                openingSubjectDTO.setSubjectId(list.get(i).getId());
                openingSubjectDAO.updateRegisterTimes(openingSubjectDTO);
        }
    }

    public List<OpeningSubjectDTO> findAll() {
        return openingSubjectDAO.findAll();
    }

    public void print() {

        List<OpeningSubjectDTO> list = openingSubjectDAO.findAll();

        char flag;

        for(OpeningSubjectDTO dto : list) {

            if (openingSubjectDAO.isRegisterPossible(dto.getRegisterStart(), dto.getRegisterClose()))
                flag = 'O';
            else flag = 'X';
            System.out.println(dto + " isAvailable : " + flag);
        }
    }

    public List<Map<String, Object>> selectByGradeOrProfessorId(int grade, String professorName){

        List<Map<String, Object>> list = openingSubjectDAO.selectByGradeOrProfessorId(grade, professorName);

        return list;
    }

    public boolean insertOpeningSubject(OpeningSubjectDTO openingSubjectDTO, List<LectureTimeTableDTO> lectureTimeTableDTOList) {

        boolean result = openingSubjectDAO.insertOpeningSubject(openingSubjectDTO, lectureTimeTableDTOList);

        return result;

    }

    public int updateCapacity(OpeningSubjectDTO openingSubjectDTO) {

        int result = openingSubjectDAO.updateCapacity(openingSubjectDTO);

        return result;

    }

//    public int updateLectureRoom(OpeningSubjectDTO openingSubjectDTO) {
//
//        int result = openingSubjectDAO.updateLectureRoom(openingSubjectDTO);
//
//        return result;
//
//    }



}
