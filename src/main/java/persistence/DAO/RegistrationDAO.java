package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.RegistrationDTO;
import persistence.DTO.StudentDTO;
import persistence.mapper.Registration.RegistrationMapper;

import java.util.List;
import java.util.Map;

public class RegistrationDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public RegistrationDAO(SqlSessionFactory sqlSessionFactory){ this.sqlSessionFactory = sqlSessionFactory; }

    public List<RegistrationDTO> findRegistrationWithStdId(String stdId){
        List<RegistrationDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);

        try{
            list = mapper.selectByMyID(stdId);
        }catch(Exception e) {
            e.printStackTrace();
            session.rollback();
        }finally{
            session.close();
        }

        return list;
    }

    public int insertRegistration(Map params){
        SqlSession session = sqlSessionFactory.openSession();
        RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);

        int result = 0;

        try{
            result = mapper.insertRegistration(params);
            session.commit();
        } catch(Exception e){
            e.printStackTrace();
            session.rollback();
        } finally{
            session.close();
        }

        return result;
    }



    public int deleteRegistration(Map params){
        SqlSession session = sqlSessionFactory.openSession();
        RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);

        int result = 0;

        try{
            result = mapper.deleteRegistration(params);
            session.commit();
        }catch(Exception e){
            e.printStackTrace();
            session.rollback();
        } finally{
            session.close();
        }

        return result;
    }

    public List<StudentDTO> getRegisteredStudentsByOpeningSubjectId(long openingSubjectId, int page, int registered){
        List<StudentDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        RegistrationMapper mapper = session.getMapper(RegistrationMapper.class);

        try{
            list  = mapper.getStdListByOpeningSubjectId(openingSubjectId,page,registered);
        } finally{
            session.close();
        }
        return list;
    }


}