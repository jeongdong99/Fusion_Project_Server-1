package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.StudentTimeTableDTO;
import persistence.mapper.StudentTimeTable.StudentTimeTableMapper;

import java.util.List;

public class StudentTimeTableDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public StudentTimeTableDAO(SqlSessionFactory sqlSessionFactory){ this.sqlSessionFactory = sqlSessionFactory; }

    public List<StudentTimeTableDTO> selectByStdId(String stdId){

        SqlSession session = sqlSessionFactory.openSession();

        StudentTimeTableMapper mapper = session.getMapper(StudentTimeTableMapper.class);
        List<StudentTimeTableDTO> studentTimeTableDTOS = mapper.selectByStdId(stdId);

        return studentTimeTableDTOS;
    }

    public void insert(int lectureTimeTableId, String stdId) {

        SqlSession session = sqlSessionFactory.openSession();

        StudentTimeTableMapper mapper = session.getMapper(StudentTimeTableMapper.class);
        mapper.insertTimeTable(lectureTimeTableId, stdId);

        try {
            session.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

    }

    public void delete(int lectureTimeTableId, String stdId) {

        SqlSession session = sqlSessionFactory.openSession();

        StudentTimeTableMapper mapper = session.getMapper(StudentTimeTableMapper.class);
        mapper.deleteTimeTable(lectureTimeTableId, stdId);

        try {
            session.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }

    }
}
