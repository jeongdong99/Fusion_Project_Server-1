package persistence.mapper.StudentTimeTable;

import org.apache.ibatis.annotations.*;
import persistence.DTO.StudentTimeTableDTO;
import persistence.mapper.Registration.RegistrationsSql;

import java.util.List;

public interface StudentTimeTableMapper {

    @SelectProvider(type = StudentTimeTableSql.class, method = "selectByStdId")
    @Results(id="resultSet1", value = {  //결과는 이렇게 매핑해서 받을 것이다
            @Result(property = "studentTimeTableId", column = "student_time_table_id"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "lectureTimeTableId", column = "lecture_time_table_id")
    })
    public List<StudentTimeTableDTO> selectByStdId(String stdId);

    @InsertProvider(type = StudentTimeTableSql.class, method = "insert")
    public void insertTimeTable(@Param("lectureTimeTableId") int lectureTimeTableId, @Param("stdId") String stdId);

    @DeleteProvider(type = StudentTimeTableSql.class, method = "delete")
    public void deleteTimeTable(@Param("lectureTimeTableId") int lectureTimeTableId, @Param("stdId") String stdId);
}
