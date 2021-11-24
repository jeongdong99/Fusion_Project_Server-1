package controller.server.thread;

import controller.protocol.Protocol;
import persistence.DAO.AdminDAO;
import persistence.DAO.LectureTimeTableDAO;
import persistence.DAO.ProfessorDAO;
import persistence.DAO.StudentDAO;
import persistence.DTO.*;
import service.*;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistrationServerThread extends Thread{

    private Socket socket;
    InputStream is;
    OutputStream os;
    Protocol protocol;
    ByteArrayInputStream bais;
    DataInputStream dis;

    int loginTried = 0;

    public RegistrationServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        open();
        System.out.println("스레드 생성 완료");


        byte[] header = new byte[Protocol.LEN_HEADER_SIZE];
        byte[] body;

        try {

            while(true){

                int flag;
                System.out.println("대기중...");
                is.read(header);
                System.out.println("데이터 수신!");

                int bodyLength = byteToInt(header, 3);

                body = new byte[bodyLength];
                if (bodyLength != 0) is.read(body);

                bais = new ByteArrayInputStream(body);
                dis = new DataInputStream(bais);

                int actionType = header[Protocol.INDEX_ACTION];
                int targetType = header[Protocol.INDEX_CODE];

                switch (actionType) {
                    case Protocol.LOGIN:

                        login(header, body);
                        break;

                    case Protocol.LOGOUT:
                        switch (targetType) {
                            case Protocol.ADMIN:
                            case Protocol.STUDENT:
                            case Protocol.PROFESSOR:
                        }
                        break;
                    case Protocol.CREATE:
                        switch (targetType) {
                            case Protocol.ADMIN:
                            case Protocol.STUDENT:
                            case Protocol.PROFESSOR:
                            case Protocol.SUBJECT:
                            case Protocol.OPENING_SUBJECT:
                            case Protocol.REGISTRATION:
                            case Protocol.SYLLABUS:
                        }
                        break;
                    case Protocol.READ:
                        switch (targetType) {
                            case Protocol.ADMIN:
                            case Protocol.STUDENT:
                            case Protocol.PROFESSOR:
                            case Protocol.SUBJECT:
                            case Protocol.LECTURE_TIME_TABLE:
                            case Protocol.OPENING_SUBJECT:
                            case Protocol.REGISTRATION:
                            case Protocol.STUDENT_TIME_TABLE:
                            case Protocol.SYLLABUS:
                        }
                        break;
                    case Protocol.UPDATE:
                        switch (targetType) {
                            case Protocol.ADMIN:
                            case Protocol.STUDENT:
                            case Protocol.PROFESSOR:
                            case Protocol.SUBJECT:
                            case Protocol.LECTURE_TIME_TABLE:
                            case Protocol.OPENING_SUBJECT:
                            case Protocol.SYLLABUS:
                        }
                        break;
                    case Protocol.DELETE:
                        switch (targetType) {
                            case Protocol.ADMIN:
                            case Protocol.STUDENT:
                            case Protocol.PROFESSOR:
                            case Protocol.SUBJECT:
                            case Protocol.OPENING_SUBJECT:
                            case Protocol.REGISTRATION:
                            case Protocol.SYLLABUS:
                        }
                        break;

                }
            }

        } catch (IOException e) { e.printStackTrace(); }
    }

    private void login(byte[] header, byte[] body) throws IOException {

        int userType = header[Protocol.INDEX_CODE];
        int bodyLength = byteToInt(header, Protocol.INDEX_BODY_LENGTH);

        String id = dis.readUTF();
        String pw = dis.readUTF();

        System.out.println("userType : " + userType);
        System.out.println("id : " + id);
        System.out.println("pw : " + pw);

        if (userType == Protocol.ADMIN) adminLogin(id, pw, loginTried++);
        else if (userType == Protocol.STUDENT) stdLogin(id, pw, loginTried);
        else if (userType == Protocol.PROFESSOR) professorLogin(id, pw, loginTried);

    }

    private int byteToInt(byte[] data, int pos) {

        int result = ((int) (data[pos] & 0xff) << 8) |
                     ((int) data[pos+1] & 0xff);

        ArrayList arr = new ArrayList();

        return result;

    }

    private void open(){
        try{
            is = socket.getInputStream();
            os = socket.getOutputStream();
            protocol = new Protocol();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    AdminService adminService = new AdminService(new AdminDAO());
    LectureTimeTableService lectureTimeTableService = new LectureTimeTableService();
    OpeningSubjectService openingSubjectService = new OpeningSubjectService();
    ProfessorService professorService = new ProfessorService(new ProfessorDAO());
    RegistrationService registrationService = new RegistrationService();
    StudentService stdService = new StudentService(new StudentDAO());
    StudentTimeTableService stdTableService = new StudentTimeTableService();
    SubjectService subjectService = new SubjectService();
    DepartmentService departmentService = new DepartmentService();

    // 프로토콜 사용법
    // Protocol protocol = new Protocol();
    // protocol.setHeader(1, 0, 0, 0, 0, 0); >> 관리자 로그인 요청
    // String id = "adminID";
    // byte[] tmp = id.getBytes();
    // protocol.addBody(tmp, 0, )

    //로그인
    private void adminLogin(String id, String password, int loginCount){

        if(loginCount <= 5){
            //5회 실패 패킷 전송
        }

        //관리자 로그인
        AdminDTO adminDTO = adminService.login(id, password);
        System.out.println("result : " + adminDTO);
        //성공 여부 패킷 전송
//        Protocol protocol = new Protocol();
//        if(adminDTO.getAdminId() != null) { //성공
//            protocol.setHeader((byte) 1, (byte) 0, (byte) 10, (byte) 0, (byte) 0, 0);
//        }else{ //실패
//            protocol.setHeader((byte) 1, (byte) 0, (byte) 9, (byte) 0, (byte) 0, 0);
//        }
    }

    private void professorLogin(String id, String password, int loginCount){

        if(loginCount <= 5){
            //5회 실패 패킷 전송
        }

        //교수 로그인
        ProfessorDTO professorDTO = professorService.login(id, password);

        //성공 여부 패킷 전송
    }

    private void stdLogin(String id, String password, int loginCount){

        if(loginCount <= 5){
            //5회 실패 패킷 전송
        }

        //학생 로그인
        StudentDTO studentDTO = stdService.login(id, password);

        //성공 여부 패킷 전송
    }

    //로그아웃
    private void logout(){
        //로그아웃 성공 메세지
            //연결 종료
        //로그아웃 실패??
    }

    //교수 생성
    private void professorInsert(byte[] header, byte[] body) {
        ProfessorDTO professorDTO;
        String name, password, phoneNumber, professorId, departmentName;
        int departmentId;

        int flag = 0;

        int dataLength = byteToInt(body, flag);
        flag += 2;

        professorId = new String(body, flag, dataLength);
        flag += dataLength;

        dataLength = byteToInt(body, flag);
        flag += 2;
        departmentName = new String(body, flag, dataLength);

        //departmentName으로 departmentId를 받아오는 기능
        departmentId = departmentService.selectDepartmentIdByDepartName(departmentName);

        professorDTO = new ProfessorDTO(name, password, phoneNumber, departmentId, professorId);

        professorService.insertProfessor(professorDTO);

        //교수 생성 성공 패킷 전송

    }

    //학생 생성
    private void stdInsert(byte[] header, byte[] body){
        StudentDTO studentDTO;
        String name, password, phoneNumber, studentId, departmentName;
        int departmentId;
        int grade;

        int flag = 0;

        int dataLength = byteToInt(body, flag);
        flag += 2;

        studentId = new String(body, flag, dataLength);
        flag += dataLength;

        dataLength = byteToInt(body, flag);
        flag += 2;
        departmentName = new String(body, flag, dataLength);

        //departmentName으로 departmentId를 받아오는 기능
        departmentId = departmentService.selectDepartmentIdByDepartName(departmentName);

        studentDTO = new StudentDTO(name, password, phoneNumber, studentId, departmentId, grade);

        stdService.insertStudent(studentDTO);

        //학생 생성 성공 패킷 전송

    }

    //교과목 생성
    private void subjectInsert(byte[] header, byte[] body){
        SubjectDTO subjectDTO;
        int targetGrade, semester, credit;
        String subjectCode, subjectName, phoneNumber;

        int flag = 0;

        int dataLength = byteToInt(body, flag);
        flag += 2;

        studentId = new String(body, flag, dataLength);
        flag += dataLength;

        dataLength = byteToInt(body, flag);
        flag += 2;
        departmentName = new String(body, flag, dataLength);

        subjectDTO = new SubjectDTO(subjectCode, subjectName, targetGrade, semester, credit);

        subjectService.insertOneSubject(subjectDTO);


        //교과목 생성 성공 패킷 전송

    }

    //교과목 수정
    private void updateSubject(byte[] header, byte[] body){
        //기능 구현 필요
        SubjectDTO subjectDTO;
        int targetGrade, semester, credit;
        String subjectCode, subjectName, phoneNumber;

        subjectDTO = new SubjectDTO(subjectCode, subjectName, targetGrade, semester, credit);
        subjectService.updateSubject(subjectDTO);
        //교과목 수정 결과 응답
    }

    //교과목 삭제
    private void deleteSubject(/*String subjectCode or String subjectName*/){
        //기능 구현 필요

        //교과목 삭제 결과 응답
    }

    //강의계획서 입력 기간 설정
    private void settingSyllabusInputPeriod(LocalDateTime startTime, LocalDateTime closeTime){
        //기능 구현 필요

        //강의계획서 입력 기간 설정 결과 응답
    }

    //학년별 수강 신청 기간 설정
    private void settingRegistrationPeriod(int tagetGrade, LocalDateTime startTime, LocalDateTime closeTime){
        OpeningSubjectDTO openingSubjectDTO = new OpeningSubjectDTO();
        openingSubjectDTO.setRegisterStart(startTime);
        openingSubjectDTO.setRegisterClose(closeTime);
        openingSubjectService.updateRegisterTime(tagetGrade, openingSubjectDTO);

        //학년별 수강 신청 기간 설정 결과 응답
    }

    //교수 정보 조회
    private void selectProfessorInfo(/*교수 id or 교수 이름*/){
        //기능 구현 필요

        //교수 정보 조회 결과 응답
    }

    //학생 정보 조회
    private void selectStdInfo(String stdId){
        //기능 구현 필요
        //stdId가 null이면 전체 조회, 있으면 그 학생 조회

        //학생 정보 조회 결과 응답
    }

    //개설 교과목 생성
    private void createOpeningSubject(List<OpeningSubjectDTO> openingSubjectDTOS, List<LectureTimeTableDTO> lectureTimeTableDTOS){
        for(int i = 0; i < openingSubjectDTOS.size(); i++){
            openingSubjectService.insertOpeningSubject(openingSubjectDTOS.get(i), lectureTimeTableDTOS);
            //lectureTimeTable List 맞음?
            //boolean 여러개니깐 받아서 어떻게 할지?
        }

        //개설 교과목 생성 결과 응답
    }

    //개설 교과목 수정
    private void updateOpeningSubject(List<OpeningSubjectDTO> openingSubjectDTOS){
        for(int i = 0; i < openingSubjectDTOS.size(); i++){
            //기능 구현? or 지금 구현되어 있는 capacity update
        }

        //개설 교과목 수정 결과 응답
    }

    //개설 교과목 삭제
    private void deleteOpeningSubject(OpeningSubjectDTO openingSubjectDTO){
        //기능 구현(DTO에서 개설 교과목 id 뽑아서 파라미터로 넘겨줌)

        //개설 교과목 삭제 결과 응답
    }

    //개설 교과목 정보 조회
    private void selectOpeningSubjectInfo(/*dynamic 조회?*/){
        openingSubjectService.findAll();
//        openingSubjectService.selectByGradeOrProfessorId();

        //개설 교과목 정보 조회 결과 응답
    }

    //교수 비밀번호 수정
    private void updateProfessorPassword(ProfessorDTO professorDTO){
        //기능 구현 필요

        //교수 비밀번호 수정 결과 응답
    }

    //교수 개인정보 수정
    private void updateProfessorInfo(ProfessorDTO professorDTO){
        //기능 구현 필요

        //교수 비밀번호 수정 결과 응답
    }

    //강의계획서 입력
    private void syllabusInput(/*List<SyllabusDto> syllabusDtos*/){
        //기능 구현 필요

        //강의계획서 입력 결과 응답
    }

    //강의계획서 수정
    private void updateSyllabus(/*List<SyllabusDto> syllabusDtos*/){
        //기능 구현 필요

        //강의계획서 수정 결과 응답
    }

    //담당 교과목 조회
    private void selectResponsibilitySubject(List<ProfessorDTO> professorDTOS){
        List<Map<String, Object>> list =
                openingSubjectService.selectByGradeOrProfessorId(0, professorDTOS.get(0).getName()); //어차피 이름을 하나만 받을꺼니깐?

        //담당 교과목 조회 결과 전송
    }

    //담당 교과목 강의 계획서 조회
    private void selectResponsibilitySyllabus(List<ProfessorDTO> professorDTOS){
        //professorDTOS의 이름으로 담당 교과목 검색하여 담당 교과목 id검색하여 강의 계획서 조회

        //담당 교과목 강의 계획서 조회
    }

    //담당 교과목 시간표
    private void professorTimeTable(/*List<ProfessorDTO> professorDTOS*/){
        //교수 id로 시간표 겁색 기능 구현 필요

        //담당 교과목 시간표 결과 응답
    }

    //학생 개인정보 수정
    private void updateStdInfo(List<StudentDTO> studentDTOS){
        //학생 정보 수정 기능 구현 필요(현재 이름 바꾸는 기능은 있음)

        //학생 정보 수정 결과 응답
    }

    //학생 비밀번호 수정
    private void updateStdPassword(List<StudentDTO> studentDTOS){
        //학생 비밀번호 수정 기능 구현 필요

        //학생 비밀번호 수정 결과 응답
    }

    //수강 신청
    private void registration(){

    }

    //수강 신청 취소

    //개설 교과목 목록(전학년) 조회

    //선택 개설 교과목 강의 계획서 조회

    //본인 시간표 조회
}
