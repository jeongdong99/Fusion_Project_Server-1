package persistence.mapper.LectureTimeTable;

import org.apache.ibatis.jdbc.SQL;

public class LectureTimeTableSql {

    public String selectByOpeningSubjectId(){
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("lecture_time_tables");
            WHERE("opening_subject_id = #{openingSubjectId}");
        }};

        return sql.toString();
    }

    public String selectByLectureTimeTableId(){
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("lecture_time_tables");
            WHERE("lecture_time_table_id = #{lectureTimeTableId}");
        }};

        return sql.toString();
    }

    public String insert() {

        SQL sql = new SQL() {{

            INSERT_INTO("lecture_time_tables");
            VALUES("opening_subject_id", "#{openingSubjectId}");
            VALUES("lecture_room_id", "#{lectureRoomId}");
            VALUES("day", "#{day}");
            VALUES("start_period", "#{startPeriod}");
            VALUES("close_period", "#{closePeriod}");
        }};

        return sql.toString();
    }

    public String updateLectureRoom() {

        SQL sql = new SQL() {{
           UPDATE("lecture_time_tables");
           SET("lecture_room_id = #{lectureRoomId}");
           WHERE("lecture_time_table_id = #{lectureTimeTableId}");
        }};

        return sql.toString();
    }

}
