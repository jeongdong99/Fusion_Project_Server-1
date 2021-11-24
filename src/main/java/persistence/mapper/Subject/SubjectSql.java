package persistence.mapper.Subject;

import org.apache.ibatis.jdbc.SQL;

public class SubjectSql {

    public String updateTargetGrade() {

        SQL sql = new SQL() {{
            UPDATE("subjects");
            SET("target_grade = #{targetGrade}");
            WHERE("subject_id = #{id}");
        }};

        return sql.toString();

    }

    public String updateSemester() {

        SQL sql = new SQL() {{
            UPDATE("subjects");
            SET("semester = #{semester}");
            WHERE("subject_id = #{id}");
        }};

        return sql.toString();

    }

    public String updateCredit() {

        SQL sql = new SQL() {{
            UPDATE("subjects");
            SET("credit = #{credit}");
            WHERE("subject_id = #{id}");
        }};

        return sql.toString();

    }
}
