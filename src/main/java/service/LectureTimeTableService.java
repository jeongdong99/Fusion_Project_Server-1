package service;

import persistence.DAO.LectureTimeTableDAO;
import persistence.DTO.LectureTimeTableDTO;
import persistence.MyBatisConnectionFactory;

import java.util.List;

public class LectureTimeTableService {
    LectureTimeTableDAO lectureTimeTableDAO = new LectureTimeTableDAO(MyBatisConnectionFactory.getSqlSessionFactory());

    public List<LectureTimeTableDTO> selectByOpeningSubjectId(int openingSubjectId){
        List<LectureTimeTableDTO> lectureTimeTableDTOS = lectureTimeTableDAO.selectByOpeningSubjectId(openingSubjectId);

        return lectureTimeTableDTOS;
    }

    public int updateLectureRoom(LectureTimeTableDTO lectureTimeTableDTO) {

        return lectureTimeTableDAO.updateLectureRoom(lectureTimeTableDTO);

    }
}
