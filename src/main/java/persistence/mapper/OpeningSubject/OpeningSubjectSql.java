package persistence.mapper.OpeningSubject;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

public class OpeningSubjectSql {

    public String insertQuery() {

        SQL sql = new SQL() {{

            INSERT_INTO("opening_subjects");
            VALUES("subject_id", "#{subjectId}");
            VALUES("registered", "#{registered}");
            VALUES("professor_id", "#{professorId}");
            VALUES("divided_class", "#{dividedClass}");
            VALUES("capacity", "#{capacity}");
            VALUES("syllabus_write_Start", "#{syllabusWriteStart}");
            VALUES("syllabus_write_close", "#{syllabusWriteClose}");
            VALUES("register_start", "#{registerStart}");
            VALUES("register_close", "#{registerClose}");

        }};

        return sql.toString();
    }

    public String updateCapacity() {

        SQL sql = new SQL() {{
            UPDATE("opening_subjects");
            SET("capacity = #{capacity}");
            WHERE("opening_subject_id = #{openingSubjectId}");
        }};

        return sql.toString();

    }

    public String registeredPlus(long openingSubjectId) {

        SQL sql = new SQL() {{
            UPDATE("opening_subjects");
            SET("registered = registered + 1");
            WHERE("opening_subject_id = #{openingSubjectId}");
        }};

        return sql.toString();

    }

    public String registeredMinus(long openingSubjectId) {

        SQL sql = new SQL() {{
            UPDATE("opening_subjects");
            SET("registered = registered - 1");
            WHERE("opening_subject_id = #{openingSubjectId}");
        }};

        return sql.toString();


    }

    public String updateRegisterStartClose() {
        SQL sql = new SQL() {{
            UPDATE("opening_subjects");
            SET("register_start=#{registerStart}", "register_close=#{registerClose}");
            WHERE("subject_id=#{subjectId}");
        }};
        return sql.toString();
    }

    public String getByOpeningSubjectId(long openingSubjectId){
        SQL sql = new SQL() {{
            SELECT("opening_subject_id, registered");
            FROM("opening_subjects");
            WHERE("opening_subject_id = #{openingSubjectId}");
        }};

        return sql.toString();
    }

    public String selectByGradeOrProfessorId(final int grade, final String professorName){
        SQL sql = new SQL() {{

            SELECT_DISTINCT("opening_subjects.opening_subject_id, subject_name, name, room_number");
            SELECT_DISTINCT("start_period, close_period, credit");
            SELECT_DISTINCT("register_start, register_close");
            SELECT_DISTINCT("divided_class, subject_code, day");
            SELECT_DISTINCT("lecture_time_table_id");
//            SELECT_DISTINCT("");
//            SELECT_DISTINCT("");
//            SELECT_DISTINCT("");
//            SELECT_DISTINCT("");
//            SELECT_DISTINCT("opening_subjects.*");
//            SELECT_DISTINCT("name, room_number, lecture_time_tables.*");
            FROM("opening_subjects");
            INNER_JOIN("lecture_time_tables ON lecture_time_tables.opening_subject_id = opening_subjects.opening_subject_id");
            INNER_JOIN("lecture_rooms ON lecture_rooms.lecture_room_id = lecture_time_tables.lecture_room_id");
            INNER_JOIN("subjects ON subjects.subject_id = opening_subjects.subject_id");
            INNER_JOIN("professors ON professors.professor_id = opening_subjects.professor_id");
            INNER_JOIN("users ON users.user_id = professors.user_id");
            if(grade != 0){
                WHERE("target_grade = #{param1}");
            }
            if(professorName != null){
                WHERE("name = #{param2}");
            }
            ORDER_BY("opening_subjects.subject_id");

        }};

        return sql.toString();
    }

    public String getLastInsertId(){
        SQL sql = new SQL(){{
            SELECT("LAST_INSERT_ID()");
        }};

        return sql.toString();
    }

    public String selectOneBySubjectIdAndDividedClass(){
        SQL sql = new SQL() {{
            SELECT("*");
            FROM("opening_subjects");
            WHERE("subject_id = #{subjectId} AND divided_class = #{dividedClass}");
        }};

        return sql.toString();
    }




}
