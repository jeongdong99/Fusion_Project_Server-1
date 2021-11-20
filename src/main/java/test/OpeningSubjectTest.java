package test;

import persistence.DTO.LectureTimeTableDTO;
import persistence.DTO.OpeningSubjectDTO;
import persistence.Enum.Day;
import service.OpeningSubjectService;

import java.util.ArrayList;
import java.util.List;

public class OpeningSubjectTest {

    public static void insertTest() {

        OpeningSubjectService openingSubjectService = new OpeningSubjectService();

        // 컴퓨터 네트워크
        OpeningSubjectDTO dto = new OpeningSubjectDTO();
        dto.setCapacity(3);
        dto.setSubjectId(80);
        dto.setProfessorId("PF000001");
        dto.setRegistered(0);
        dto.setDividedClass("01");

        LectureTimeTableDTO timeDTO1 = new LectureTimeTableDTO();
        timeDTO1.setDay(Day.THU);
        timeDTO1.setStartPeriod(6);
        timeDTO1.setClosePeriod(7);
        timeDTO1.setLectureRoomId(7);

        LectureTimeTableDTO timeDTO2 = new LectureTimeTableDTO();
        timeDTO2.setDay(Day.FRI);
        timeDTO2.setStartPeriod(3);
        timeDTO2.setClosePeriod(4);
        timeDTO2.setLectureRoomId(7);

        List<LectureTimeTableDTO> list = new ArrayList<>();
        list.add(timeDTO1);
        list.add(timeDTO2);

        openingSubjectService.insertOpeningSubject(dto, list);

        // 운영체제
        dto = new OpeningSubjectDTO();
        dto.setCapacity(3);
        dto.setSubjectId(79);
        dto.setProfessorId("PF000003");
        dto.setRegistered(0);
        dto.setDividedClass("01");

        timeDTO1 = new LectureTimeTableDTO();
        timeDTO1.setDay(Day.MON);
        timeDTO1.setStartPeriod(6);
        timeDTO1.setClosePeriod(6);
        timeDTO1.setLectureRoomId(7);

        timeDTO2 = new LectureTimeTableDTO();
        timeDTO2.setDay(Day.WED);
        timeDTO2.setStartPeriod(1);
        timeDTO2.setClosePeriod(2);
        timeDTO2.setLectureRoomId(7);

        list = new ArrayList<>();
        list.add(timeDTO1);
        list.add(timeDTO2);

        openingSubjectService.insertOpeningSubject(dto, list);

        // 융합 프로젝트
        dto = new OpeningSubjectDTO();
        dto.setCapacity(3);
        dto.setSubjectId(81);
        dto.setProfessorId("PF000001");
        dto.setRegistered(0);
        dto.setDividedClass("01");

        timeDTO1 = new LectureTimeTableDTO();
        timeDTO1.setDay(Day.WED);
        timeDTO1.setStartPeriod(1);
        timeDTO1.setClosePeriod(2);
        timeDTO1.setLectureRoomId(8);


        list = new ArrayList<>();
        list.add(timeDTO1);

        openingSubjectService.insertOpeningSubject(dto, list);

        // c++
        dto = new OpeningSubjectDTO();
        dto.setCapacity(3);
        dto.setSubjectId(78);
        dto.setProfessorId("PF000006");
        dto.setRegistered(0);
        dto.setDividedClass("02");

        timeDTO1 = new LectureTimeTableDTO();
        timeDTO1.setDay(Day.WED);
        timeDTO1.setStartPeriod(6);
        timeDTO1.setClosePeriod(7);
        timeDTO1.setLectureRoomId(8);

        timeDTO2 = new LectureTimeTableDTO();
        timeDTO2.setDay(Day.FRI);
        timeDTO2.setStartPeriod(6);
        timeDTO2.setClosePeriod(7);
        timeDTO2.setLectureRoomId(8);

        list = new ArrayList<>();
        list.add(timeDTO1);
        list.add(timeDTO2);

        openingSubjectService.insertOpeningSubject(dto, list);

        // 디자인패턴
        dto = new OpeningSubjectDTO();
        dto.setCapacity(3);
        dto.setSubjectId(83);
        dto.setProfessorId("PF000005");
        dto.setRegistered(0);
        dto.setDividedClass("01");

        timeDTO1 = new LectureTimeTableDTO();
        timeDTO1.setDay(Day.MON);
        timeDTO1.setStartPeriod(6);
        timeDTO1.setClosePeriod(7);
        timeDTO1.setLectureRoomId(11);

        timeDTO2 = new LectureTimeTableDTO();
        timeDTO2.setDay(Day.FRI);
        timeDTO2.setStartPeriod(1);
        timeDTO2.setClosePeriod(2);
        timeDTO2.setLectureRoomId(11);

        list = new ArrayList<>();
        list.add(timeDTO1);
        list.add(timeDTO2);

        openingSubjectService.insertOpeningSubject(dto, list);

        // 컴파일러
        dto = new OpeningSubjectDTO();
        dto.setCapacity(3);
        dto.setSubjectId(84);
        dto.setProfessorId("PF000006");
        dto.setRegistered(0);
        dto.setDividedClass("01");

        timeDTO1 = new LectureTimeTableDTO();
        timeDTO1.setDay(Day.MON);
        timeDTO1.setStartPeriod(6);
        timeDTO1.setClosePeriod(6);
        timeDTO1.setLectureRoomId(12);

        timeDTO2 = new LectureTimeTableDTO();
        timeDTO2.setDay(Day.WED);
        timeDTO2.setStartPeriod(3);
        timeDTO2.setClosePeriod(4);
        timeDTO2.setLectureRoomId(12);

        list = new ArrayList<>();
        list.add(timeDTO1);
        list.add(timeDTO2);

        openingSubjectService.insertOpeningSubject(dto, list);

        // 자바프로그래밍
        dto = new OpeningSubjectDTO();
        dto.setCapacity(3);
        dto.setSubjectId(77);
        dto.setProfessorId("PF000005");
        dto.setRegistered(0);
        dto.setDividedClass("02");

        timeDTO1 = new LectureTimeTableDTO();
        timeDTO1.setDay(Day.MON);
        timeDTO1.setStartPeriod(8);
        timeDTO1.setClosePeriod(9);
        timeDTO1.setLectureRoomId(11);

        timeDTO2 = new LectureTimeTableDTO();
        timeDTO2.setDay(Day.THU);
        timeDTO2.setStartPeriod(8);
        timeDTO2.setClosePeriod(9);
        timeDTO2.setLectureRoomId(11);

        list = new ArrayList<>();
        list.add(timeDTO1);
        list.add(timeDTO2);

        openingSubjectService.insertOpeningSubject(dto, list);

        // 개설 강좌 전체 출력 && 수강 신청 가능 여부 표시
        openingSubjectService.print();


    }


}
