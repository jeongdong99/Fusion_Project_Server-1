package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.SubjectDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubjectDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public SubjectDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int selectIdBySubjectCode(String subjectCode){
        SqlSession session = sqlSessionFactory.openSession();
        SubjectDTO dto = new SubjectDTO();

        try{
            dto = session.selectOne("mapper.SubjectMapper.selectIdBySubjectCode", subjectCode);
        }finally
        {
            session.close();
        }

        if (dto == null) return 0;
        else return dto.getId();

    }

    public List<SubjectDTO> selectAll(){

        List<SubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.SubjectMapper.selectAll");
        }finally
        {
            session.close();
        }
        return list;
    }

    public List<SubjectDTO> selectByGrade(int grade){
        List<SubjectDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.SubjectMapper.selectByGrade", grade);
        }finally
        {
            session.close();
        }
        return list;
    }

    public void insertOneSubject(SubjectDTO subjectDTO){
        SqlSession session = sqlSessionFactory.openSession();

        try{
            session.insert("mapper.SubjectMapper.insertOneSubject", subjectDTO);
            session.commit();
        }finally{
            session.close();
        }
    }

    public void updateSubjectNameWithName(Map params){
        SqlSession session = sqlSessionFactory.openSession();

        try{
            session.update("mapper.SubjectMapper.updateOneSubjectName", params);
            session.commit();
        }finally{
            session.close();
        }
    }



}