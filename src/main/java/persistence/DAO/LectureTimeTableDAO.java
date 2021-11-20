package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.LectureTimeTableDTO;
import persistence.mapper.LectureTimeTable.LectureTimeTableMapper;

import java.util.List;

public class LectureTimeTableDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public LectureTimeTableDAO(SqlSessionFactory sqlSessionFactory){ this.sqlSessionFactory = sqlSessionFactory; }

    public List<LectureTimeTableDTO> selectByOpeningSubjectId(int openingSubjectId){
        SqlSession session = sqlSessionFactory.openSession();

        LectureTimeTableMapper mapper = session.getMapper(LectureTimeTableMapper.class);
        List<LectureTimeTableDTO> lectureTimeTableDTOS = mapper.selectByOpeningSubjectId(openingSubjectId);

        return lectureTimeTableDTOS;
    }

    public List<LectureTimeTableDTO> selectByLectureTimeTableId(int lectureTimeTableId){
        SqlSession session = sqlSessionFactory.openSession();

        LectureTimeTableMapper mapper = session.getMapper(LectureTimeTableMapper.class);
        List<LectureTimeTableDTO> lectureTimeTableDTOS = mapper.selectByLectureTimeTableId(lectureTimeTableId);

        return lectureTimeTableDTOS;
    }

    public int updateLectureRoom(LectureTimeTableDTO lectureTimeTableDTO) {

        SqlSession session = sqlSessionFactory.openSession();

        LectureTimeTableMapper mapper = session.getMapper(LectureTimeTableMapper.class);
        int result = mapper.updateLectureRoom(lectureTimeTableDTO);
        System.out.println(result);

        session.commit();

        return result;

    }

    public boolean isOverlapped(LectureTimeTableDTO dto1, LectureTimeTableDTO dto2) {

        if (dto1.getDay().toString().equals(dto2.getDay().toString())) {

            int dto1Start = dto1.getStartPeriod();
            int dto1Close = dto1.getClosePeriod();
            int dto2Start = dto2.getStartPeriod();
            int dto2Close = dto2.getClosePeriod();

            if (dto1Start <= dto2Start && dto2Start <= dto1Close) return true;
            if (dto1Start <= dto2Close && dto2Close <= dto1Close) return true;
            else return false;

        } else return false;
    }




}
