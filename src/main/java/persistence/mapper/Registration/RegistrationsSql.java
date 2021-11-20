package persistence.mapper.Registration;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class RegistrationsSql {

    public String selectMyRegistration(String stdId){
        SQL sql = new SQL(){{
            SELECT("*");
            FROM("REGISTRATIONS");
            WHERE("student_id = #{stdId}");
        }};
        return sql.toString();
    }

    public String insertRegistration(Map<String, Object> map){
        SQL sql = new SQL(){{
            INSERT_INTO("REGISTRATIONS");
            VALUES("opening_subject_id, student_id", "#{openingSubjectId}, #{stdId}");
        }};

        return sql.toString();
    }

    public String deleteRegistration(Map map){
        SQL sql = new SQL(){{
           DELETE_FROM("REGISTRATIONS");
           WHERE("opening_subject_id = #{openingSubjectId} AND student_id = #{stdId}");
        }};

        return sql.toString();
    }

    public String getStdList(final long openingSubjectId,final int page,final int registered ){
        SQL sql = new SQL(){{
            SELECT("students.*,users.name,users.phone_number");
            FROM("students");
            INNER_JOIN("registrations ON students.student_id = registrations.student_id");
            INNER_JOIN("users ON users.user_id = students.user_id");
            WHERE("registrations.opening_subject_id = #{param1}");
            ORDER_BY("student_id");
            LIMIT("#{param3}");
            OFFSET("#{param2}");
        }};
        return sql.toString();

    }


}
