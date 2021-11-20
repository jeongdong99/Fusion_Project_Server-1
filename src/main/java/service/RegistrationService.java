package service;

import persistence.DAO.*;
import persistence.DTO.*;
import persistence.MyBatisConnectionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationService {

    RegistrationDAO registrationDAO = new RegistrationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
    OpeningSubjectDAO openingSubjectDAO = new OpeningSubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());
    LectureTimeTableDAO lectureTimeTableDAO = new LectureTimeTableDAO(MyBatisConnectionFactory.getSqlSessionFactory());
    StudentTimeTableDAO studentTimeTableDAO = new StudentTimeTableDAO(MyBatisConnectionFactory.getSqlSessionFactory());


    public List<RegistrationDTO> findRegistrationWithStdId(String stdId){

        List<RegistrationDTO> list = registrationDAO.findRegistrationWithStdId(stdId);

        return list;
    }

    public void insertRegistration(String openingSubjectCode, String stdId) { //openingSubjectCode은 분반이 포함된 코드

        String[] codeArr = openingSubjectCode.split("-");
        String subjectCode = codeArr[0];
        String dividedClass = codeArr[1];

        SubjectDAO subjectDAO = new SubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        int subjectId = subjectDAO.selectIdBySubjectCode(subjectCode); //subject_id를 가져옴

        int openingSubjectId = openingSubjectDAO.selectIdBySubjectIdAndDividedClass(subjectId, dividedClass);


        OpeningSubjectDTO openingSubjectDTO = openingSubjectDAO.selectById(openingSubjectId);

        // ==============================================================================================

        List<RegistrationDTO> registrationDTOS = registrationDAO.findRegistrationWithStdId(stdId);

        // registrationDTOS.stream().forEach(v -> System.out.println(v.toString()));

        for(RegistrationDTO registrationDTO : registrationDTOS){
            if(registrationDTO.getOpeningSubjectId() == openingSubjectId){
                System.out.println("중복신청은 불가합니다.");
                return;
            }
        }

        // lctDOTS2
        List<LectureTimeTableDTO> subjectTimeTable = lectureTimeTableDAO.selectByOpeningSubjectId(openingSubjectId); // 신청할 교과목의 강의시간들
        List<StudentTimeTableDTO> personalTimeTable = studentTimeTableDAO.selectByStdId(stdId); // 현재 나의 강의 시간표
        List<LectureTimeTableDTO> personalTimeTableElements;

        for(StudentTimeTableDTO studentTimeTableDTO : personalTimeTable){
            int i = 0;
            personalTimeTableElements = lectureTimeTableDAO.selectByLectureTimeTableId(studentTimeTableDTO.getLectureTimeTableId());
            for(LectureTimeTableDTO lectureTimeTableDTO : personalTimeTableElements){
                LectureTimeTableDTO lectureTime = subjectTimeTable.get(i);
                i++;
                if(lectureTimeTableDAO.isOverlapped(lectureTimeTableDTO, lectureTime)){
                    System.out.println("강의시간이 겹칩니다.");
                    return;
                }
            }
        }

        // ==============================================================================================

        int capacity = openingSubjectDTO.getCapacity();
        int registered = openingSubjectDTO.getRegistered();

        if(registered < capacity){
            Map<String, Object> params = new HashMap<>();
            params.put("openingSubjectId", openingSubjectId);
            params.put("stdId", stdId);

            int result = registrationDAO.insertRegistration(params);
            if (result != 0) {
                openingSubjectDAO.registeredPlus(openingSubjectId);
                for(LectureTimeTableDTO dto : subjectTimeTable) {
                    studentTimeTableDAO.insert(dto.getLectureTimeTableId(), stdId);
                }
                System.out.println("수강신청 성공");
            }

        }else{ System.out.println("최대 수강 제한인원을 초과하였습니다."); }

    }

    public void deleteRegistration(String openingSubjectCode, String stdId) {

        String[] codeArr = openingSubjectCode.split("-");
        String subjectCode = codeArr[0];
        String dividedClass = codeArr[1];

        SubjectDAO subjectDAO = new SubjectDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        int subjectId = subjectDAO.selectIdBySubjectCode(subjectCode); //subject_id를 가져옴

        /*if (subjectId == 0) {

            System.out.println("존재하지 않는 교과목입니다.");
            return;

        }*/

        int openingSubjectId = openingSubjectDAO.selectIdBySubjectIdAndDividedClass(subjectId, dividedClass);

        List<LectureTimeTableDTO> subjectTimeTable = lectureTimeTableDAO.selectByOpeningSubjectId(openingSubjectId);

        Map<String, Object> params = new HashMap<>();
        params.put("openingSubjectId", openingSubjectId);
        params.put("stdId", stdId);

        int result = registrationDAO.deleteRegistration(params);
        if(result != 0) {

            openingSubjectDAO.registeredMinus(openingSubjectId);

            for(LectureTimeTableDTO dto : subjectTimeTable) {
                studentTimeTableDAO.delete(dto.getLectureTimeTableId(), stdId);
            }

        }

    }

    public List<StudentDTO> getStdListByOpeningSubjectId(long openingSubjectId, int page) {

        // OpeningSubjectDAO openingSubjectDAO = new OpeningSubjectDAO((MyBatisConnectionFactory.getSqlSessionFactory()));
        OpeningSubjectDTO openingSubjectDTO =openingSubjectDAO.getByOpeningSubjectId(openingSubjectId);
        if(openingSubjectDTO==null) {System.out.println("해당하는 개설 강좌가 없습니다."); return null;};
        if(page<1){System.out.println("해당하는 페이지가 없습니다."); return null;};
        int tmp = openingSubjectDTO.getRegistered();
        int divide = 0;
        if (tmp % 2 == 0) divide = tmp / 2;
        else divide = tmp / 2 + 1;
        page = divide*(page-1);
        // RegistrationDAO registrationDAO = new RegistrationDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<StudentDTO> list = registrationDAO.getRegisteredStudentsByOpeningSubjectId(openingSubjectDTO.getOpeningSubjectId(),page,divide);
        // list.stream().forEach(v-> System.out.println(v.toString()));

        return list;
    }





}