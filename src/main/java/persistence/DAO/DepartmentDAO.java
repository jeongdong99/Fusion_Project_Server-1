package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.mapper.Department.DepartmentMapper;

public class DepartmentDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public DepartmentDAO(SqlSessionFactory sqlSessionFactory){ this.sqlSessionFactory = sqlSessionFactory; }

    public int selectDepartIdByDepartName(String departmentName){
        SqlSession session = sqlSessionFactory.openSession();

       DepartmentMapper mapper = session.getMapper(DepartmentMapper.class);
       int departID = mapper.selectDepartIdByDepartName(departmentName).getDepartmentId();

        return departID;
    }
}
