package persistence.mapper.OpeningSubject;

import org.apache.ibatis.annotations.*;
import persistence.DTO.OpeningSubjectDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface OpeningSubjectMapper {

    @InsertProvider(type = OpeningSubjectSql.class, method = "insertQuery")
    boolean insertOpeningSubject(OpeningSubjectDTO openingSubjectDTO);

    @UpdateProvider(type = OpeningSubjectSql.class, method = "updateCapacity")
    int updateCapacity(OpeningSubjectDTO openingSubjectDTO);

    @UpdateProvider(type = OpeningSubjectSql.class, method = "registeredPlus")
    int registeredPlus(long openingSubjectId);

    @UpdateProvider(type = OpeningSubjectSql.class, method = "registeredMinus")
    int registeredMinus(long openingSubjectId);

    @UpdateProvider(type = OpeningSubjectSql.class, method = "updateRegisterStartClose")
    void updateNewRegisterTime(
            @Param("subjectId") int subject_id,
            @Param("registerStart") LocalDateTime register_start,
            @Param("registerClose") LocalDateTime register_close
    );

    @Select("SELECT * FROM opening_subjects")
    @Results(id = "openingSubjectResultSet", value = {
            @Result(property = "openingSubjectId", column = "opening_subject_id" ),
            @Result(property = "subjectId", column = "subject_id" ),
            @Result(property = "registered", column = "registered" ),
            @Result(property = "dividedClass", column = "divided_class"),
            @Result(property = "professorId", column = "professor_id" ),
            @Result(property = "capacity", column = "capacity" ),
            @Result(property = "syllabusWriteStart", column = "syllabus_write_start" ),
            @Result(property = "syllabusWriteClose", column = "syllabus_write_close" ),
            @Result(property = "registerStart", column = "register_start" ),
            @Result(property = "registerClose", column = "register_close" )
    })
    List<OpeningSubjectDTO> findAll();

    @Select("SELECT * FROM opening_subjects WHERE opening_subject_id = #{id}")
    @ResultMap("openingSubjectResultSet")
    OpeningSubjectDTO selectById(@Param("id") long openingSubjectId);

    // 마이바티스2 교안 18페이지 참고
//    @SelectProvider()
//    @ResultMap("openingSubjectResultSet")
//    List<OpeningSubjectDTO> selectByGradeAndProfessor();

    @SelectProvider(type = OpeningSubjectSql.class, method = "getByOpeningSubjectId")
    @Results(id = "getByOpeningSubjectIdResultSet", value = {
            @Result(property = "openingSubjectId",column = "opening_subject_id"),
            @Result(property = "registered", column = "registered" )
    })
    OpeningSubjectDTO getByOpeningSubjectId(long  openingSubjectId);

    @SelectProvider(type = OpeningSubjectSql.class, method = "selectByGradeOrProfessorId")
    List<Map<String, Object>> selectByGradeOrProfessorId(int grade, String professorName);

    @SelectProvider(type = OpeningSubjectSql.class, method = "getLastInsertId")
    public int getLastInsertId();

    @SelectProvider(type = OpeningSubjectSql.class, method = "selectOneBySubjectIdAndDividedClass")
    @ResultMap("openingSubjectResultSet")
    public OpeningSubjectDTO selectOneBySubjectIdAndDividedClass(@Param("subjectId") int subjectId, @Param("dividedClass") String dividedClass);





}


