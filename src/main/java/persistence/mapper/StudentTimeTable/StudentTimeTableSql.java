package persistence.mapper.StudentTimeTable;

import org.apache.ibatis.jdbc.SQL;

public class StudentTimeTableSql {

    public String selectByStdId(){

        SQL sql = new SQL(){{
            SELECT("*");
            FROM("student_time_tables");
            WHERE("student_id = #{stdId}");
        }};

        return sql.toString();
    }

    public String insert(){
        SQL sql = new SQL(){{
            INSERT_INTO("student_time_tables");
            VALUES("lecture_time_table_id, student_id", "#{lectureTimeTableId}, #{stdId}");
        }};

        return sql.toString();
    }

    public String delete(){
        SQL sql = new SQL(){{
            DELETE_FROM("student_time_tables");
            WHERE("lecture_time_table_id = #{lectureTimeTableId}");
            AND();
            WHERE("student_id = #{stdId}");
        }};

        return sql.toString();
    }
}
