package service;

import persistence.DAO.DepartmentDAO;
import persistence.MyBatisConnectionFactory;

public class DepartmentService {

    DepartmentDAO departmentDAO = new DepartmentDAO(MyBatisConnectionFactory.getSqlSessionFactory());

    public int selectDepartmentIdByDepartName(String departName){
        int departId = departmentDAO.selectDepartIdByDepartName(departName);

        return departId;
    }
}
