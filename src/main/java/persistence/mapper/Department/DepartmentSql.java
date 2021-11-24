package persistence.mapper.Department;

import org.apache.ibatis.jdbc.SQL;

public class DepartmentSql {
    public String selectDepartIdByDepartName(){
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("departments");
            WHERE("department_name = #{departmentName}");
        }};

        return sql.toString();
    }
}
