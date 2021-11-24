package service;

import persistence.MyBatisConnectionFactory;
import persistence.DAO.SubjectDAO;
import persistence.DTO.SubjectDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectService {
    SubjectDAO subjectDAO = new SubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());

    public List<SubjectDTO> selectAll(){
        List<SubjectDTO> list = subjectDAO.selectAll();

        return list;
    }

    public List<SubjectDTO> selectByGrade(int grade){
        List<SubjectDTO> list = subjectDAO.selectByGrade(grade);

        return list;
    }

    //insert subject기능
    public void insertOneSubject(SubjectDTO subjectDTO){
        subjectDAO.insertOneSubject(subjectDTO);
    }

    public void updateSubjectNameWithName(String subjectCode, String newSubjectName){
        Map<String, Object> params = new HashMap<>();
        params.put("subjectCode", subjectCode);
        params.put("newSubjectName", newSubjectName);

        subjectDAO.updateSubjectNameWithName(params);
    }

    public void updateSubject(SubjectDTO subjectDTO){
        int subjectId = subjectDAO.selectIdBySubjectCode(subjectDTO.getSubjectCode());

        if(subjectDTO.getTargetGrade() != 0){
            subjectDAO.updateTargetGrade(subjectDTO.getTargetGrade(), subjectId);
        }

        if(subjectDTO.getSubjectName() != null){
            updateSubjectNameWithName(subjectDTO.getSubjectCode(), subjectDTO.getSubjectName());
        }

        if(subjectDTO.getCredit() != 0){
            subjectDAO.updateCredit(subjectDTO.getCredit(), subjectId);
        }

        if(subjectDTO.getSemester() != 0){
            subjectDAO.updateSemester(subjectDTO.getSemester(), subjectId);
        }
    }

}