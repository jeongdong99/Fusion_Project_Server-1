package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.LectureTimeTableDTO;
import persistence.DTO.OpeningSubjectDTO;
import persistence.MyBatisConnectionFactory;
import persistence.mapper.LectureTimeTable.LectureTimeTableMapper;
import persistence.mapper.OpeningSubject.OpeningSubjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OpeningSubjectDAO {

    private SqlSessionFactory sqlSessionFactory = null;
    private LectureTimeTableDAO lectureTimeTableDAO = new LectureTimeTableDAO(MyBatisConnectionFactory.getSqlSessionFactory());

    public OpeningSubjectDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public boolean insertOpeningSubject(OpeningSubjectDTO openingSubjectDTO, List<LectureTimeTableDTO> lectureTimeTableDTOList) {

        SqlSession session = sqlSessionFactory.openSession();

        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);
        boolean result = mapper.insertOpeningSubject(openingSubjectDTO);
        // session.commit();
        // int lastInserted = mapper.getLastInsertId();

        if(result){

            int lastInserted = mapper.getLastInsertId();
            session.commit();

            for(LectureTimeTableDTO lectureTimeTableDTO : lectureTimeTableDTOList) {

                lectureTimeTableDTO.setOpeningSubjectId(lastInserted);
                LectureTimeTableMapper lttMapper = session.getMapper(LectureTimeTableMapper.class);
                lttMapper.insertLectureTimeTable(lectureTimeTableDTO);

            }
        }

        session.commit();
        // session.close();

        if (result) return true;
        else return false;

    }

    public int updateCapacity(OpeningSubjectDTO openingSubjectDTO) {

        SqlSession session = sqlSessionFactory.openSession();

        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);
        int result = mapper.updateCapacity(openingSubjectDTO);

        session.commit();
        session.close();

        return result;

    }

//    public int updateLectureRoom(OpeningSubjectDTO openingSubjectDTO, int lectureRoomId) {
//
//        SqlSession session = sqlSessionFactory.openSession();
//
//        LectureTimeTableMapper mapper = session.getMapper(LectureTimeTableMapper.class);
//        LectureTimeTableDTO lectureTimeTableDTO = new LectureTimeTableDTO();
//        lectureTimeTableDTO.setOpeningSubjectId(openingSubjectDTO.getOpeningSubjectId());
//        lectureTimeTableDTO.setLectureRoomId(lectureRoomId);
//        int result = mapper.updateLectureRoom(lectureTimeTableDTO);
//
//        session.commit();
//
//        return result;
//
//
//    }

    public int registeredPlus(long openingSubjectId) {

        SqlSession session = sqlSessionFactory.openSession();

        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);
        int result = mapper.registeredPlus(openingSubjectId);

        session.commit();
        session.close();

        return result;

    }

    public int registeredMinus(long openingSubjectId) {

        SqlSession session = sqlSessionFactory.openSession();

        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);
        int result = mapper.registeredMinus(openingSubjectId);

        session.commit();
        session.close();

        return result;

    }

    public List<OpeningSubjectDTO> findAll() {

        SqlSession session = sqlSessionFactory.openSession();

        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);
        List<OpeningSubjectDTO> openingSubjectDTOList = mapper.findAll();

        return openingSubjectDTOList;
    }

    public void updateRegisterTimes(OpeningSubjectDTO openingSubjectDTO){
        SqlSession session = sqlSessionFactory.openSession();
        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);
        try{
            mapper.updateNewRegisterTime(openingSubjectDTO.getSubjectId(), openingSubjectDTO.getRegisterStart(), openingSubjectDTO.getRegisterClose());
            session.commit();
        } catch(Exception e){
            e.printStackTrace();
            session.rollback();
        } finally{
            session.close();
        }
    }

    public OpeningSubjectDTO selectById(long openingSubjectId) {

        SqlSession session = sqlSessionFactory.openSession();
        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);

        return mapper.selectById(openingSubjectId);

    }

    public boolean isRegisterPossible(LocalDateTime startTime, LocalDateTime closeTime){
        if (startTime == null || closeTime == null ) return false;

        if(startTime.isBefore(LocalDateTime.now()) && closeTime.isAfter(LocalDateTime.now())){
            return true;
        }
        return false;
    }

    public OpeningSubjectDTO getByOpeningSubjectId(long openingSubjectId){
        OpeningSubjectDTO openingSubjectDTO = null;
        SqlSession session = sqlSessionFactory.openSession();
        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);
        try{
            openingSubjectDTO = mapper.getByOpeningSubjectId(openingSubjectId);
        } finally{
            session.close();
        }
        return openingSubjectDTO;
    }

    public List<Map<String, Object>> selectByGradeOrProfessorId(int grade, String professorName){
        SqlSession session = sqlSessionFactory.openSession();
        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);

        List<Map<String, Object>> list;
        try{
            list = mapper.selectByGradeOrProfessorId(grade, professorName);
        } finally{
            session.close();
        }

        return list;
    }

    public int selectIdBySubjectIdAndDividedClass(int subjectId, String dividedClass){
        SqlSession session = sqlSessionFactory.openSession();
        OpeningSubjectMapper mapper = session.getMapper(OpeningSubjectMapper.class);

        OpeningSubjectDTO dto = mapper.selectOneBySubjectIdAndDividedClass(subjectId, dividedClass);

        if(dto != null) return (int) dto.getOpeningSubjectId();
        else return 0;

    }






}
