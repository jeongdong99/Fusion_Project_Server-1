package persistence.mapper.Subject;

import org.apache.ibatis.annotations.*;
import persistence.DTO.SubjectDTO;

import java.util.List;

public interface SubjectMapper {

    @UpdateProvider(type = SubjectSql.class, method = "updateTargetGrade")
    public void updateTargetGrade(int targetGrade, int id);

    @UpdateProvider(type = SubjectSql.class, method = "updateSemester")
    public void updateSemester(int semester, int id);

    @UpdateProvider(type = SubjectSql.class, method = "updateCredit")
    public void updateCredit(int credit, int id);

}
