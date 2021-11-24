package persistence.mapper.Department;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import persistence.DTO.DepartmentDTO;

import java.util.List;

public interface DepartmentMapper {

    @SelectProvider(type = DepartmentSql.class, method = "selectDepartIdByDepartName")
    @Results(id = "resultSet1", value = {
            @Result(property = "DepartmentId", column = "department_id" ),
            @Result(property = "DepartmentName", column = "department_name" ),

    })
    public DepartmentDTO selectDepartIdByDepartName(String departmentName);
}
