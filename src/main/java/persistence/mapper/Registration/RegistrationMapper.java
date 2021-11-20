package persistence.mapper.Registration;

import org.apache.ibatis.annotations.*;
import persistence.DTO.RegistrationDTO;
import persistence.DTO.StudentDTO;

import java.util.List;
import java.util.Map;

public interface RegistrationMapper {

    @InsertProvider(type = RegistrationsSql.class, method = "insertRegistration")
    int insertRegistration(Map<String, Object> map);

    @SelectProvider(type = RegistrationsSql.class, method = "selectMyRegistration")
    @Results(id="registrationResultSet", value = {  //결과는 이렇게 매핑해서 받을 것이다
            @Result(property = "registrationId", column = "registration_id"),
            @Result(property = "openingSubjectId", column = "opening_subject_id"),
            @Result(property = "studentId", column = "student_id")
    })
    List<RegistrationDTO> selectByMyID(String studentId);

    @DeleteProvider(type = RegistrationsSql.class, method = "deleteRegistration")
    int deleteRegistration(Map map);

    @SelectProvider(type = RegistrationsSql.class, method = "getStdList")
    @Results(id = "StudentDTOResultSet", value = {
            @Result(property = "userId",column = "user_id"),
            @Result(property = "studentId", column = "student_id" ),
            @Result(property = "grade", column = "grade" ),
            @Result(property = "departmentId", column = "department_id" ),
            @Result(property = "name", column = "name" ),
            @Result(property = "phoneNumber", column = "phone_number" )
    })
    List<StudentDTO> getStdListByOpeningSubjectId(long opening_subject_id, int page, int registered );



}
