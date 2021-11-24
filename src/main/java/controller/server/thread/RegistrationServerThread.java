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
import java.util.List;

public class RegistrationServerThread extends Thread{

    private Socket socket;
    InputStream is;
    OutputStream os;

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

            while(true) {

                int flag;
                System.out.println("대기중...");
                is.read(header);
                System.out.println("데이터 수신!");

                int bodyLength = byteToInt(header, 3);

                body = new byte[bodyLength];
                if (bodyLength != 0) is.read(body);

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

    private void login(byte[] header, byte[] body) {

        int userType = header[2];
        int bodyLength = byteToInt(header, 3);

        String id;
        String pw;

        int flag = 0;

        int dataLength = byteToInt(body, flag);
        flag += 2;

        id = new String(body, flag, dataLength);
        flag += dataLength;

        dataLength = byteToInt(body, flag);
        flag += 2;
        pw = new String(body, flag, dataLength);

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

        return result;

    }

    private void open(){
        try{
            is = socket.getInputStream();
            // bi = new BufferedInputStream(is);
            os = socket.getOutputStream();
            // bo = new BufferedOutputStream(os);
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
    private void professorInsert(List<ProfessorDTO> list) {
//        for(int i = 0; i < list.size(); i++) {
//            professorService.insertProfessor(list.get(i));
//        }

        //교수 생성 성공 패킷 전송

    }

    //학생 생성
    private void stdInsert(/*List<StudentDTO> list*/){
//        for(int i = 0; i < list.size(); i++){
//            stdService.insertStudent(list.get(i));
//        }

        //학생 생성 성공 패킷 전송

    }

    //교과목 생성
    private void subjectInsert(/*List<SubjectDTO> list*/){
//        for(int i = 0; i < list.size(); i++){
//            subjectService.insertOneSubject(list.get(i));
//        }

        //교과목 생성 성공 패킷 전송

    }

    //교과목 수정
    private void changeSubject(/*변경할 Data*/){
        //기능 구현 필요

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
    private void changeOpeningSubject(List<OpeningSubjectDTO> openingSubjectDTOS){
        for(int i = 0; i < openingSubjectDTOS.size(); i++){
            //기능 구현? or 지금 구현되어 있는 capacity update
        }

        //개설 교과목 수정 결과 응답
    }

    //개설 교과목 삭제
    private void deleteOpeningSubject(OpeningSubjectDTO openingSubjectDTO){
        //기능 구현(DTO에서 개설 교과목 id 뽑아서 인수로 넘겨줌)

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
    private void syllabusInput(/*SyllabusDto syllabusdto*/){
        //기능 구현 필요

        //강의계획서 입력 결과 응답
    }

    //강의계획서 수정
    private void updateSyllabus(){

    }

    //담당 교과목 조회

    //담당 교과목 강의 계획서 조회

    //담당 교과목 시간표

    //학생 개인정보 수정

    //학생 비밀번호 수정

    //수강 신청

    //수강 신청 취소

    //개설 교과목 목록(전학년) 조회

    //선택 개설 교과목 강의 계획서 조회

    //본인 시간표 조회
}
