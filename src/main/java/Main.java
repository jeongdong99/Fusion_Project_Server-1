import persistence.DAO.*;
import persistence.DTO.*;
import persistence.Enum.Day;
import persistence.MyBatisConnectionFactory;
import service.*;
import test.OpeningSubjectTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String args[]) {

        // 01. 회원 CRU
        // admin insert
        /*AdminDTO adminDTO = new AdminDTO(
                "어드민",
                "pass",
                "010-2337-5555",
                "ADMIN2"
        );

        AdminService adminService = new AdminService(new AdminDAO());
        adminService.insertAdmin(adminDTO);


        System.out.println("=====================admin find all========================");
        adminService.findAll().stream().forEach(v -> System.out.println(v.toString()));
        System.out.println("===========================================================");*/

        // professor insert
        /*ProfessorDTO pf1 = new ProfessorDTO(
                "김선명",
                "20211110",
                "010-2523-2522",
                1,
                "PF000001"
        );
        ProfessorDTO pf2 = new ProfessorDTO(
                "김성렬",
                "20211110",
                "010-2317-1287",
                1,
                "PF000002"
        );

        ProfessorService pfService = new ProfessorService(new ProfessorDAO());
        pfService.insertProfessor(pf1);
        pfService.insertProfessor(pf2);
        System.out.println("=====================professor find all========================");
        pfService.findAll().stream().forEach(v -> System.out.println(v.toString()));
        System.out.println("===========================================================");*/

        // student insert
        /*StudentDTO std1 = new StudentDTO(
                "안태욱",
                "password",
                "010-2535-2666",
                "20180671",
                1,
                2
        );
        StudentDTO std2 = new StudentDTO(
                "손정동",
                "password",
                "010-2567-1444",
                "20180596",
                1,
                2
        );
        StudentDTO std3 = new StudentDTO(
                "김현석",
                "password",
                "010-2444-2333",
                "20180335",
                1,
                2
        );
        StudentDTO std4 = new StudentDTO(
                "이충엽",
                "password",
                "010-2555-6777",
                "20180950",
                1,
                2
        );

        StudentService stdService = new StudentService(new StudentDAO());
        stdService.insertStudent(std1);
        stdService.insertStudent(std2);
        stdService.insertStudent(std3);
        stdService.insertStudent(std4);

        System.out.println("=====================student find all========================");
        stdService.findAll().stream().forEach(v -> System.out.println(v.toString()));
        System.out.println("===========================================================");*/

        // update professor phoneNumber
        /*ProfessorService pfService = new ProfessorService(new ProfessorDAO());
        ProfessorDTO pfDTO = new ProfessorDTO();
        pfDTO.setProfessorId("PF000002");
        pfDTO.setPhoneNumber("010-0100-1122");
        System.out.println("===============Before Update==================");
        pfService.findAll().stream().forEach(v -> System.out.println(v.toString()));
        pfService.updatePhoneNumber(pfDTO);
        System.out.println("===============After Update==================");
        pfService.findAll().stream().forEach(v -> System.out.println(v.toString()));*/

        // update student name
        /*StudentService stdService = new StudentService(new StudentDAO());
        StudentDTO stdDTO = new StudentDTO();
        stdDTO.setStudentId("20180671");
        stdDTO.setName("김금오");
        System.out.println("===============Before Update==================");
        stdService.findAll().stream().forEach(v -> System.out.println(v.toString()));
        stdService.updateName(stdDTO);
        System.out.println("===============After Update==================");
        stdService.findAll().stream().forEach(v -> System.out.println(v.toString()));*/

        //------------------------------------2번 구현시작----------------------------------------------
        // 02. 교과목
        SubjectService subjectService = new SubjectService();

        //1학년 과목
        SubjectDTO java = new SubjectDTO("CS0010", "자바프로그래밍", 1, 2, 3);

        //2학년 과목
        SubjectDTO network = new SubjectDTO("CS0016", "컴퓨터네트워크", 2, 2, 4);
        SubjectDTO operationSys = new SubjectDTO("CS0017", "운영체제", 2, 2, 3);
        SubjectDTO fusionProj = new SubjectDTO("CS0069", "융합프로젝트", 2, 2, 2);
        SubjectDTO cplus = new SubjectDTO("CS0077", "C++프로그래밍", 2, 2, 3);
        SubjectDTO openSrc = new SubjectDTO("CS0080", "오픈소스소프트웨어", 2, 2, 2);
        SubjectDTO scienceEnglish = new SubjectDTO("LA0292", "과학기술영어독해", 2, 2, 2);
        SubjectDTO probability = new SubjectDTO("BA0029", "확률및통계", 2, 2, 3);

        //3학년 과목
        SubjectDTO designPattern = new SubjectDTO("CS0027", "디자인패턴", 3, 2, 3);

        //4학년 과목
        SubjectDTO compiler = new SubjectDTO("CS0035", "컴파일러", 4, 2, 3);

        //교과목 생성
        /*subjectService.insertOneSubject(java);
        subjectService.insertOneSubject(cplus);
        subjectService.insertOneSubject(operationSys);
        subjectService.insertOneSubject(network);
        subjectService.insertOneSubject(fusionProj);
        subjectService.insertOneSubject(openSrc);
        subjectService.insertOneSubject(designPattern);
        subjectService.insertOneSubject(compiler);
        subjectService.insertOneSubject(scienceEnglish);
        subjectService.insertOneSubject(probability);

        //교과목 전체 조회
        subjectService.selectAll().stream().forEach(v -> System.out.println(v.toString()));*/

        //교과목 학년별 조회
        /*System.out.println("======================1학년===================");
        subjectService.selectByGrade(1).stream().forEach(v -> System.out.println(v.toString()));;
        System.out.println("======================2학년===================");
        subjectService.selectByGrade(2).stream().forEach(v -> System.out.println(v.toString()));;
        System.out.println("======================3학년===================");
        subjectService.selectByGrade(3).stream().forEach(v -> System.out.println(v.toString()));;
        System.out.println("======================4학년===================");
        subjectService.selectByGrade(4).stream().forEach(v -> System.out.println(v.toString()));;*/

        //과목명 변경( C++프로그래밍 => C++ )
        /*System.out.println("=============before update==================");
        subjectService.selectByGrade(2).stream().forEach(v -> System.out.println(v.toString()));
        subjectService.updateSubjectNameWithName("CS0077", "C++");
        System.out.println("=============after update==================");
        subjectService.selectByGrade(2).stream().forEach(v -> System.out.println(v.toString()));*/

        //------------------------------------2번 구현 끝----------------------------------------------


        // 03. 개설 교과목 CRU
        OpeningSubjectService openingSubjectService = new OpeningSubjectService();

        // opening subject insert && 전체 조회 && 수강신청 가능 여부 표시
        // OpeningSubjectTest.insertTest();

        // Dynamic 조회 && 전체 조회
        /*List<Map<String, Object>> list = openingSubjectService.selectByGradeOrProfessorId(0, null);

        for(Map map : list) {

            System.out.print("[" + map.get("subject_code") + "-");
            System.out.print(map.get("divided_class") + "] ");
            System.out.print(map.get("subject_name") + " ");
            System.out.print(map.get("name") + " ");
            System.out.print(map.get("room_number") + " ");
            System.out.print(map.get("day") + " ");
            System.out.print(map.get("start_period") + "~");
            System.out.print(map.get("close_period") + "교시 ");
            System.out.print(map.get("credit") + "학점 ");
            System.out.print(map.get("credit") + "학점 ");
            System.out.println();

        }*/

        // 전체 조회2
        // openingSubjectService.findAll().forEach(v -> System.out.println(v.toString()));

        // update capacit
        /*OpeningSubjectDTO dto = new OpeningSubjectDTO();
        dto.setOpeningSubjectId(48);
        dto.setCapacity(30);
        System.out.println("==========================before update======================");
        openingSubjectService.findAll().forEach(v -> System.out.println(v.toString()));
        openingSubjectService.updateCapacity(dto);
        System.out.println("=========================after update=======================");
        openingSubjectService.findAll().forEach(v -> System.out.println(v.toString()));*/

        // update lectureRoom
        /*List<Map<String, Object>> list = openingSubjectService.selectByGradeOrProfessorId(0, null);

        for(Map map : list) {

            System.out.print("[" + map.get("subject_code") + "-");
            System.out.print(map.get("divided_class") + "] ");
            System.out.print(map.get("subject_name") + " ");
            System.out.print("[lecture_time_table_id : " + map.get("lecture_time_table_id") + "] ");
            System.out.print(map.get("room_number") + " ");
            System.out.print(map.get("day") + " ");
            System.out.print(map.get("start_period") + "~");
            System.out.print(map.get("close_period") + "교시 ");
            System.out.println();

        }

        LectureTimeTableDTO dto = new LectureTimeTableDTO();
        dto.setLectureTimeTableId(42);
        dto.setLectureRoomId(1);
        LectureTimeTableService service = new LectureTimeTableService();
        service.updateLectureRoom(dto);

        list = openingSubjectService.selectByGradeOrProfessorId(0, null);
        System.out.println("================================================================");

        for(Map map : list) {

            System.out.print("[" + map.get("subject_code") + "-");
            System.out.print(map.get("divided_class") + "] ");
            System.out.print(map.get("subject_name") + " ");
            System.out.print("[lecture_time_table_id : " + map.get("lecture_time_table_id") + "] ");
            System.out.print(map.get("room_number") + " ");
            System.out.print(map.get("day") + " ");
            System.out.print(map.get("start_period") + "~");
            System.out.print(map.get("close_period") + "교시 ");
            System.out.println();

        }*/


        // 04. 수강신청 기간 설정
        //registration period setting
        /*openingSubjectService.print();
        OpeningSubjectDTO openingSubjectDTO = new OpeningSubjectDTO();
        // OpeningSubjectDAO openingSubjectDAO = new OpeningSubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        // OpeningSubjectService openingSubjectService = new OpeningSubjectService();

        openingSubjectDTO.setRegisterStart(LocalDateTime.of(2021, 11, 10, 0, 0, 0));
        openingSubjectDTO.setRegisterClose(LocalDateTime.of(2021, 11, 10, 23,59, 59));
        openingSubjectService.updateRegisterTime(2, openingSubjectDTO);

        //수강신청 가능 여부 출력
        openingSubjectService.print();*/

        // 05. 수강 신청
        //------------------------------------5번 구현 시작----------------------------------------------

        RegistrationService registrationService = new RegistrationService();

        //수강 신청(학생 시점)    융합프로젝트 1분반 신청 상황
        /*registrationService.insertRegistration("CS0069-01", "20180671");
        /// c++
        registrationService.insertRegistration("CS0077-02", "20180671");*/

        //학생 본인 수강신청 현황 조회
        System.out.println("나의 신청 내역");
        // registrationService.findRegistrationWithStdId("20180671").stream().forEach(v -> System.out.println(v.toString()));

        //수강신청 취소
        /*registrationService.deleteRegistration("CS0077-02", "20180671");
        registrationService.findRegistrationWithStdId("20180671").stream().forEach(v -> System.out.println(v.toString()));*/

        //------------------------------------5번 구현 끝----------------------------------------------

        // 06. 수강신청 예외처리리

        // 신청해놓은 과목과 추가로 신청하려고 하는 (1 ,3, 4학년 과목)이 시간이 겹칠때 예외처리
        // openingSubjectService.print();
        // OpeningSubjectDTO openingSubjectDTO = ningSubjectDTO();
        // OpeningSubjectDAO openingSubjectDAO = new OpeningSubjecew OpentDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        // OpeningSubjectService openingSubjectService = new OpeningSubjectService();

        /*OpeningSubjectDTO openingSubjectDTO = new OpeningSubjectDTO();
        openingSubjectDTO.setRegisterStart(LocalDateTime.of(2021, 11, 10, 0, 0, 0));
        openingSubjectDTO.setRegisterClose(LocalDateTime.of(2021, 11, 10, 23,59, 59));
        openingSubjectService.updateRegisterTime(1, openingSubjectDTO);
        openingSubjectService.updateRegisterTime(3, openingSubjectDTO);
        openingSubjectService.updateRegisterTime(4, openingSubjectDTO);*/

        // 컴파일러 CS0035-01 월6
        // 운영체제 CS0017-01 월6
        // 디자인패턴 CS0027-01 월67
        registrationService.insertRegistration("CS0027-01", "20180335");

        // System.out.println("나의 신청 내역");
        // registrationService.findRegistrationWithStdId("20180335").stream().forEach(v -> System.out.println(v.toString()));


        //최대 수강 인원 초과 예외
        registrationService.insertRegistration("CS0017-01", "20180002"); // 2번째
        // registrationService.insertRegistration("CS0017-01", "20180596"); // 2번째
        // registrationService.insertRegistration("CS0017-01", "20180950"); // 2번째
        // registrationService.insertRegistration("CS0027-01", "20180596"); // 3번째
        // registrationService.insertRegistration("CS0027-01", "20180950"); // 4번째 >> 실패

       // 07. 페이징 처리
        // 해당 개설 교과목에 수강신청한 학생 조회 (교수 관점)
        // RegistrationService registrationService = new RegistrationService();
        List listt = registrationService.getStdListByOpeningSubjectId(49,2);
        // if (listt != null)
        System.out.println("=================");
            listt.stream().forEach(v -> System.out.println(v.toString()));
        System.out.println("=================");


    }

}