package persistence.mapper.LectureTimeTable;

import org.apache.ibatis.annotations.*;
import persistence.DTO.LectureTimeTableDTO;

import java.util.List;

public interface LectureTimeTableMapper {

    @SelectProvider(type = LectureTimeTableSql.class, method = "selectByOpeningSubjectId")
    @Results(id = "resultSet1", value = {
            @Result(property = "lectureTimeTableId", column = "lecture_time_table_id" ),
            @Result(property = "openingSubjectId", column = "opening_subject_id" ),
            @Result(property = "lectureRoomId", column = "lecture_room_id" ),
            @Result(property = "day", column = "day" ),
            @Result(property = "startPeriod", column = "start_period" ),
            @Result(property = "closePeriod", column = "close_period" )

    })
    public List<LectureTimeTableDTO> selectByOpeningSubjectId(int openingSubjectId);


    @SelectProvider(type = LectureTimeTableSql.class, method = "selectByLectureTimeTableId")
    @ResultMap("resultSet1")
    public List<LectureTimeTableDTO> selectByLectureTimeTableId(@Param("lectureTimeTableId") int lectureTimeTableId);

    @InsertProvider(type = LectureTimeTableSql.class, method = "insert")
    public void insertLectureTimeTable(LectureTimeTableDTO lectureTimeTableDTO);

    @UpdateProvider(type = LectureTimeTableSql.class, method = "updateLectureRoom")
    public int updateLectureRoom(LectureTimeTableDTO lectureTimeTableDTO);

}
